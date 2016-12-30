//package org.demoexm.core.util;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.springframework.beans.BeanUtils;
//
//import com.iafclub.baseTools.vo.LoanSchedule;
//import com.iafclub.business.status.StatusContants;
///**还款计划工具类
// * 
// * @author chenweixian
// *
// */
//public class LoanScheduleUtil {
//	/**总计key*/
//	public static final String SCHEDULE_TOTAL = "schedule_total";
//	/**集合key*/
//	public static final String SCHEDULE_LIST = "schedule_list";
//	/**计算后的总本金*/
//	public static final String CALCULATE_MONEY = "calculate_money";
//	/**每月收益总额*/
//	public static final String SUMMONERY = "sum_monery";
//	
//	/**日期计算
//	 * tradingStartTime 当前日期
//	 * times 添加月份
//	 * 创建人：陈惟鲜
//	 * author：chenweixian
//	 * 日期时间：2015年11月25日下午3:44:49
//	 */
//	public static String timeSubtraction(String tradingStartTime, String loanPeriod){
//		Date result = timeSubtraction(tradingStartTime, loanPeriod, StatusContants.TERM_UNIT_MONTH.getIndex());
//		return MyDateUtil.formatDate(result);
//	}
//	
//	/**日期计算
//	 * tradingStartTime 当前日期
//	 * loanPeriod 期限
//	 * loanPeriodFlag 期限类型
//	 * 创建人：陈惟鲜
//	 * author：chenweixian
//	 * 日期时间：2015年11月25日下午3:44:49
//	 */
//	public static Date timeSubtraction(String tradingStartTime, String loanPeriod,String loanPeriodFlag){
//		Date result = null;
//		BigDecimal month = new BigDecimal(loanPeriod);
//		Date tradingStartTimeDate = MyDateUtil.dateStrToDate(tradingStartTime);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(tradingStartTimeDate);
//		if (StatusContants.TERM_UNIT_DAY.getIndex().equals(loanPeriodFlag)){// 天
//			calendar.add(Calendar.DATE, month.intValue());// 天
//		}else if (StatusContants.TERM_UNIT_MONTH.getIndex().equals(loanPeriodFlag)){// 月
//			calendar.add(Calendar.MONTH, month.intValue());// 加一个月
//		}
//		result = calendar.getTime();
//			
//		
//		return result;
//	}
//	
//	/**获取格式化对象
//	 * 创建人：陈惟鲜
//	 * author：chenweixian
//	 * 日期时间：2015年12月3日下午6:54:07
//	 */
//	public static DecimalFormat getDecimalFormat(){
//		DecimalFormat df = new DecimalFormat("####0.00");// 保留小数点后两位
//		return df;
//	}
//	
//	/**
//	 * 还款账单 
//	 * 
//	 * @param loanAmount 还款金额 <i>(注:传入金额最后一位整数必须为货币计量单位分)</i>
//	 * @param loanPeriod 还款期限
//	 * @param rates 还款年利率
//	 * @param repayment 还款方式
//	 * @param tradingStartTime 还款日
//	 * @return List<Map<String,String>>( 键值 principal：存储本金 键值 interest：存储利息 键值 benxi：存储本息 键值endDate：还款日期)
//	 * 
//	 */
//	public static Map<String, Object> billMonth(String loanAmount,String loanPeriod, String rates, String repayment,String tradingStartTime) {
//		List<LoanSchedule> list = new ArrayList<LoanSchedule>();// list集合
//		/* 数据暂定为 */
//		BigDecimal loanmoney = new BigDecimal(loanAmount);// 借款金额
//		BigDecimal totalmomey = loanmoney; // 贷款总金额
//		totalmomey = MyNumberUtils.mulx(totalmomey,100);  //传入金额为分，因为计算刻度为整数，顾将计算刻度增加2位做于精度计算,因数据库存储为分为整数最后一个单位，故计算完成后需要将计算结果除去2位计算刻度
//		BigDecimal rate = new BigDecimal(rates);// 年利率
//		BigDecimal paramRate = new BigDecimal(100);// 利率参数(数据库存储为例如：20)
//		BigDecimal paramMonth = new BigDecimal(12);// 一年12个月
//		BigDecimal taxrate = rate.divide(paramRate, 6, BigDecimal.ROUND_HALF_UP); // 当前利率 (年利率)
//		BigDecimal tax = taxrate.divide(paramMonth, 9, BigDecimal.ROUND_HALF_UP); // 当前利率 (月利率)
//		BigDecimal time = new BigDecimal(loanPeriod);// 借款期限（月）
//		BigDecimal num = new BigDecimal(1);// 用于计算本息
//		BigDecimal root = num.add(tax);// 跟
//		/***所还本金*/
//		long moneyAll = 0;
//		/***利息本息*/
//		long taxMoneyAll = 0;
//		/***所还本息*/
//		long oneMonthMoneyAll = 0;
//		if (StatusContants.PAYMENTMETHOD_STATUS_1.getIndex().equals(repayment)) {/**按月等额本金*/
//			// 根据借款期限计算每月所还本金
//			BigDecimal oneMonthMoney = totalmomey.divide(time, 6,BigDecimal.ROUND_HALF_UP);
//			for (int seqId = 1; seqId <= Integer.parseInt(loanPeriod); seqId++) {
//				// 把变量转换为BigDecimal数据格式
//				BigDecimal paramj = new BigDecimal(seqId);
//				// 计算每月所还利息
//				BigDecimal taxMoney = new BigDecimal(0);
//				if (seqId == 1) {
//					taxMoney = totalmomey.multiply(tax);
//				} else {
//					taxMoney = (totalmomey.subtract(oneMonthMoney.multiply(paramj.subtract(new BigDecimal(1))))).multiply(tax);
//				}
//
//				BigDecimal money = oneMonthMoney.add(taxMoney);// 每月所还本息
//
//				LoanSchedule loanSchedule = new LoanSchedule();
//				loanSchedule.setPaymentDate(timeSubtraction(tradingStartTime,String.valueOf(seqId)));
//				//单位转换为分
//				loanSchedule.setPrincipal(oneMonthMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月本金
//				loanSchedule.setInterest(taxMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 实际每月利息
//				loanSchedule.setPrincipalAndInterest(money.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月还款金额
//				loanSchedule.setSeqId(seqId);// 期号
//				list.add(loanSchedule);
//				
//				moneyAll = moneyAll + loanSchedule.getPrincipal();
//				taxMoneyAll = taxMoneyAll + loanSchedule.getInterest();
//				oneMonthMoneyAll = oneMonthMoneyAll + loanSchedule.getPrincipalAndInterest();
//			}
//		} else if (StatusContants.PAYMENTMETHOD_STATUS_2.getIndex().equals(repayment)) {/**按月等额本息*/
//			for (int seqId = 1; seqId <= Integer.parseInt(loanPeriod); seqId++) {
//				// 每月利息额
//				BigDecimal taxMoney = totalmomey.multiply(tax)
//						.multiply(root.pow(Integer.parseInt(loanPeriod)).subtract(root.pow(seqId - 1)))
//						.divide((root.pow(Integer.parseInt(loanPeriod)).subtract(num)),6, BigDecimal.ROUND_HALF_UP);
//				// 每月还款额
//				BigDecimal money = totalmomey.multiply(tax).multiply(root.pow(Integer.parseInt(loanPeriod)))
//						.divide((root.pow(Integer.parseInt(loanPeriod)).subtract(num)),6, BigDecimal.ROUND_HALF_UP);
//				// 每月本金
//				BigDecimal oneMonthMoney = money.subtract(taxMoney);
//				// 实际每月利率
//	//			BigDecimal monthacive = taxMoney.divide(money,6, BigDecimal.ROUND_HALF_UP);
//
//				LoanSchedule loanSchedule = new LoanSchedule();
//				loanSchedule.setPaymentDate(timeSubtraction(tradingStartTime,String.valueOf(seqId)));
//				//单位转换为分
//				loanSchedule.setPrincipal(oneMonthMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月本金
//				loanSchedule.setInterest(taxMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 实际每月利息
//				loanSchedule.setPrincipalAndInterest(money.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月还款金额
//				loanSchedule.setSeqId(seqId);// 期号
//				list.add(loanSchedule);
//
//				moneyAll = moneyAll + loanSchedule.getPrincipal();
//				taxMoneyAll = taxMoneyAll + loanSchedule.getInterest();
//				oneMonthMoneyAll = oneMonthMoneyAll + loanSchedule.getPrincipalAndInterest();
//			}
//
//		} else if (StatusContants.PAYMENTMETHOD_STATUS_3.getIndex().equals(repayment)) {/*先息后本*/
//			for (int seqId = 1; seqId <= Integer.parseInt(loanPeriod); seqId++) {
//				BigDecimal oneMonthMoney = new BigDecimal(0.0);// 每月应还金额
//				// 计算每月所还利息
//				BigDecimal taxMoney = new BigDecimal(0);
//				if (seqId == Integer.parseInt(loanPeriod)) {// 最后一个月
//					oneMonthMoney = totalmomey;
//				}
//
////				BigDecimal money = totalmomey.multiply(paramRate);// 每月所还本息
//				taxMoney = totalmomey.multiply(tax);// 利息
//				BigDecimal principalAndInterest = oneMonthMoney.add(taxMoney);// 每月所还本息
//
//				LoanSchedule loanSchedule = new LoanSchedule();
//				loanSchedule.setPaymentDate(timeSubtraction(tradingStartTime,String.valueOf(seqId)));
//				//转为分
//				loanSchedule.setPrincipal(oneMonthMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月本金
//				loanSchedule.setInterest(taxMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 实际每月利息
//				loanSchedule.setPrincipalAndInterest(principalAndInterest.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月还款金额
//				loanSchedule.setSeqId(seqId);// 期号
//				list.add(loanSchedule);
//
//				moneyAll = moneyAll + loanSchedule.getPrincipal();
//				taxMoneyAll = taxMoneyAll + loanSchedule.getInterest();
//				oneMonthMoneyAll = oneMonthMoneyAll + loanSchedule.getPrincipalAndInterest();
//			}
//		} else if (StatusContants.PAYMENTMETHOD_STATUS_4.getIndex().equals(repayment)) {/*到期一次性还款*/
//			// 本息
//			BigDecimal money = totalmomey.multiply(tax).multiply(time).add(totalmomey);
//
//			BigDecimal oneMonthMoney = totalmomey;// 本金
//			BigDecimal taxMoney = totalmomey.multiply(tax).multiply(time);// 利息
//			
//			LoanSchedule loanSchedule = new LoanSchedule();
//			loanSchedule.setPaymentDate(timeSubtraction(tradingStartTime,String.valueOf(loanPeriod)));
//			//转为分
//			loanSchedule.setPrincipal(oneMonthMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月本金
//			loanSchedule.setInterest(taxMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 实际每月利息
//			loanSchedule.setPrincipalAndInterest(money.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).longValue());// 每月还款金额
////			loanSchedule.setSeqId(Integer.parseInt(loanPeriod));// 期号
//			time = BigDecimal.ONE;
//			loanSchedule.setSeqId(time.intValue());// 期号
//			list.add(loanSchedule);
//
//			moneyAll = moneyAll + loanSchedule.getPrincipal();
//			taxMoneyAll = taxMoneyAll + loanSchedule.getInterest();
//			oneMonthMoneyAll = oneMonthMoneyAll + loanSchedule.getPrincipalAndInterest();
//		}
//		
//		LoanSchedule loanScheduleAll = new LoanSchedule();
//		loanScheduleAll.setPaymentDate(timeSubtraction(tradingStartTime,String.valueOf(loanPeriod)));
//		loanScheduleAll.setPrincipal(moneyAll);// 每月本金
//		loanScheduleAll.setInterest(taxMoneyAll);// 实际每月利息
//		loanScheduleAll.setPrincipalAndInterest(oneMonthMoneyAll);// 每月还款金额
//		loanScheduleAll.setSeqId(time.intValue());// 期号
//		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put(CALCULATE_MONEY, loanScheduleAll.getPrincipal());// 总本金
//		resultMap.put(SCHEDULE_TOTAL, loanScheduleAll);// 总结果
//		resultMap.put(SCHEDULE_LIST, list);// 明细集合
//		
//		return resultMap;
//	}
//	
//	/**
//	 * 按天，一次性还本付息
//	 * 
//	 * @param loanAmount 还款金额 <i>(注:传入金额最后一位整数必须为货币计量单位分)</i>
//	 * @param loanPeriod 还款期限
//	 * @param rates 还款年利率
//	 * @param repayment 还款方式
//	 * @return List<Map<String,String>>( 键值 principal：存储本金 键值 interest：存储利息 键值 benxi：存储本息 键值endDate：还款日期)
//	 * 
//	 */
//	public static Map<String, Object> billDay(String loanAmount,String loanPeriod, String rates, String repayment,String tradingStartTime) {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		
//		List<LoanSchedule> list = new ArrayList<LoanSchedule>();// list集合
//		LoanSchedule totalLoanSchedule = new LoanSchedule(); // 总计结果
//		/* 数据暂定为 */
//		BigDecimal totalmomey = new BigDecimal(loanAmount); // 贷款总金额
//		totalmomey = MyNumberUtils.mulx(totalmomey,100);  //传入金额为分，因为计算刻度为整数，顾将计算刻度增加2位做于精度计算,因数据库存储为分为整数最后一个单位，故计算完成后需要将计算结果除去2位计算刻度
//		BigDecimal rate = new BigDecimal(rates);// 年利率
//		BigDecimal paramRate = new BigDecimal(100);// 利率参数(数据库存储为例如：20)
//		BigDecimal taxrate = rate.divide(paramRate, 6, BigDecimal.ROUND_HALF_UP); // 当前利率 (年利率)
//		BigDecimal time = new BigDecimal(loanPeriod);// 借款期限（月）
//		
//		//利息 = 本金  * 天数  * 当前利率 / 360
//		BigDecimal taxMoney = totalmomey.multiply(time).multiply(taxrate).divide(new BigDecimal(360), 6, BigDecimal.ROUND_HALF_UP);
//		 
//		//计算金额单位置为分
//	 	taxMoney = taxMoney.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP);
//	 	totalmomey = totalmomey.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP);
//	 	
//	 	BigDecimal oneMonthMoney = totalmomey;// 本金
//	 	
//	 	// 本息
//	 	BigDecimal money = taxMoney.add(totalmomey);
//	 	
//	 	time = BigDecimal.ONE;
//		// 还款日期
//		String paymentDate = MyDateUtil.formatDate(timeSubtraction(tradingStartTime,String.valueOf(loanPeriod), StatusContants.TERM_UNIT_DAY.getIndex()));
//		LoanSchedule loanSchedule = initLoanSchedule(paymentDate, oneMonthMoney, taxMoney, money, time);
//		list.add(loanSchedule);// 填充到list集合
//		
//		totalLoanSchedule.setPaymentDate(loanSchedule.getPaymentDate());
//		totalLoanSchedule.setSeqId(time.intValue());
//
//		
//		resultMap.put(CALCULATE_MONEY, oneMonthMoney.longValue());//统计总本金
//		resultMap.put(SCHEDULE_TOTAL, totalLoanSchedule);
//		resultMap.put(SCHEDULE_LIST, list);
//		return resultMap;
//	}
//	
//	/**加载计划信息
//	 * 
//	 * @param paymentDate 还款日期
//	 * @param oneMonthMoney 每月还款本金
//	 * @param taxMoney 实际每月利息
//	 * @param money 每月还款总金额
//	 * @param monthacive 每月实际利率
//	 * @param time 期数
//	 * @return
//	 * @author 陈惟鲜
//	 * @date 2016年3月14日 下午3:07:51
//	 */
//	private static LoanSchedule initLoanSchedule(String paymentDate, BigDecimal oneMonthMoney , BigDecimal taxMoney, BigDecimal money, BigDecimal time){
//		LoanSchedule loanSchedule = new LoanSchedule();
//		loanSchedule.setPaymentDate(paymentDate);// 还款时间
//		loanSchedule.setPrincipal(oneMonthMoney.setScale(0, BigDecimal.ROUND_HALF_UP).longValue());// 每月本金
//		loanSchedule.setInterest(taxMoney.setScale(0, BigDecimal.ROUND_HALF_UP).longValue());// 实际每月利息
//		loanSchedule.setPrincipalAndInterest(money.setScale(0, BigDecimal.ROUND_HALF_UP).longValue());// 每月还款金额
////		loanSchedule.setMonthacive(monthacive);// 每月实际利率
//		loanSchedule.setSeqId(time.intValue()); // 期数
//		return loanSchedule;
//	}
//	
//	/**
//	 * 重新计算：利息=本息-本金
//	 * 
//	 * @param loanAmount  单位:分
//	 * @param loanPeriod  借款期数
//	 * @param loanPeriodFlag  借款期限类型
//	 * @param rates		利率
//	 * @param repayment	借款类型 
//	 * @param tradingStartTime 还款开始日
//	 * @return
//	 * @author：chenweixian
//	 * @date 2015年12月3日下午6:49:32
//	 */
//	public static Map<String, Object> billUpdate(String loanAmount,String loanPeriod, String loanPeriodFlag, String rates, String repayment,String tradingStartTime){
//		//TODO add 20160808 目前该算法不支持最小1块钱起投，需前端限制最小起投金额100，后续需要改进算法保证其最小1块时利息不能为负数
//		
//		Map<String, Object> resultMap = null;
//		if (StatusContants.TERM_UNIT_DAY.getIndex().equals(loanPeriodFlag) && StatusContants.PAYMENTMETHOD_STATUS_4.getIndex().equals(repayment)){// 按天且是一次性还款
//			resultMap = billDay(loanAmount, loanPeriod, rates, repayment, tradingStartTime);
//		}
//		else // if (CommonContants.TERM_UNIT_MONTH)
//		{ // 月
//			resultMap = billMonth(loanAmount, loanPeriod, rates, repayment, tradingStartTime);
//		}
////		System.out.println("******************调整前***********************");
//// 		tempShow(resultMap);
//		changeData(resultMap,  new BigDecimal(loanAmount));
////		System.out.println("******************调整后***********************");
//// 		tempShow(resultMap);
//		return resultMap;
//	}
//	/**更改数据
//	 * 
//	 * 
//	 * @author 陈惟鲜
//	 * @date 2016年3月16日 下午3:51:13
//	 */
//	private static void changeData(Map<String, Object> resultMap,BigDecimal loanAmount){
//		// 为了让金额平，重新计算
//		List<LoanSchedule> loanSchedules = (List<LoanSchedule>) resultMap.get(SCHEDULE_LIST); // 明细
//		LoanSchedule totalLoanSchedule = (LoanSchedule)resultMap.get(SCHEDULE_TOTAL); // 总计
//		BigDecimal calculateMoney = new BigDecimal(resultMap.get(CALCULATE_MONEY).toString());//计算后的总本金
//		//add by zhangfeng 20160411 start 金额调平 
//		//如果计算后的总金额和投资人的本金不相等，则补差额
//		if(loanAmount.compareTo(calculateMoney) != 0 && null != loanSchedules){ 
//			for(int i = 0 ; i < loanSchedules.size(); i++ ){
//				LoanSchedule loanSchedule =	loanSchedules.get(i);
//				//在最后一期补差额
//				//if(totalLoanSchedule.getSeqId() ==loanSchedule.getSeqId()){
//				//add by zhangfeng 20160803 在第一期补差额
//				if(1 ==loanSchedule.getSeqId()){	 
//					long lessInt =  MyNumberUtils.sub(loanAmount, calculateMoney).longValue();
//					//投资金额大于计算金额
//					 if(loanAmount.compareTo(calculateMoney) == 1){ 
//						 loanSchedule.setPrincipal(MyNumberUtils.add(new BigDecimal(loanSchedule.getPrincipal()), new BigDecimal(lessInt)).longValue());
//						 loanSchedule.setInterest(MyNumberUtils.sub(new BigDecimal(loanSchedule.getInterest()), new BigDecimal(lessInt)).longValue());
//						 loanSchedule.setPrincipalAndInterest(MyNumberUtils.add(new BigDecimal(loanSchedule.getPrincipal()),new BigDecimal(loanSchedule.getInterest())).longValue());
//					}else{
//					//投资金额小于计算金额
//						loanSchedule.setPrincipal(MyNumberUtils.sub(new BigDecimal(loanSchedule.getPrincipal()), new BigDecimal(lessInt)).longValue());
//						loanSchedule.setInterest(MyNumberUtils.add(new BigDecimal(loanSchedule.getInterest()), new BigDecimal(lessInt)).longValue());
//						loanSchedule.setPrincipalAndInterest(MyNumberUtils.add(new BigDecimal(loanSchedule.getPrincipal()),new BigDecimal(loanSchedule.getInterest())).longValue());
//					}
//				}
//			}
//		}
//		//add by zhangfeng 20160411 end
// 		if (loanSchedules != null){
// 			//重新维护统计数据
//			long interest = 0;
//			long principal = 0;
//			for (int i=1;i<=loanSchedules.size();i++){
//				LoanSchedule bean = loanSchedules.get(i-1);
//				interest = MyNumberUtils.add(new BigDecimal(interest), new BigDecimal(bean.getInterest())).longValue(); // 利息
//				principal = MyNumberUtils.add(new BigDecimal(principal), new BigDecimal(bean.getPrincipal())).longValue(); // 利息
//			}
//			LoanSchedule total = new LoanSchedule(); // 总计
//			System.out.println(JSONObject.fromObject(totalLoanSchedule));
//			BeanUtils.copyProperties(totalLoanSchedule, total);
//			total.setInterest(interest);
//			total.setPrincipal(principal);
//			total.setPrincipalAndInterest(MyNumberUtils.add(new BigDecimal(total.getInterest()), new BigDecimal(total.getPrincipal())).longValue()); // 总计
//			resultMap.put(SCHEDULE_TOTAL, total);
//			System.out.println(JSONObject.fromObject(total));
//			
// 		}
//	}
//	
//	
//	/**
//	 * 测试方法
//	 * 
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception {
//		testMain();
////		/*按月等额本金*/
////		System.out.println(JSONObject.fromObject(billUpdate("500000", "12", CommonContants.TERM_UNIT_MONTH, "10", StatusContants.PAYMENTMETHOD_STATUS_1.getIndex(), "2016-05-03 00:00:00")));
////		System.out.println("/*按月等额本金*/");
////		/*按月等额本息*/
////		System.out.println(JSONObject.fromObject(billUpdate("500000", "12", CommonContants.TERM_UNIT_MONTH, "10", StatusContants.PAYMENTMETHOD_STATUS_2.getIndex(), "2016-05-03 00:00:00")));
////		System.out.println("/*按月等额本息*/");
////		/*等额本息一次性还款*/
////		System.out.println(JSONObject.fromObject(billUpdate("500000", "12", CommonContants.TERM_UNIT_MONTH, "10", StatusContants.PAYMENTMETHOD_STATUS_3.getIndex(), "2016-05-03 00:00:00")));
////		System.out.println("/*等额本息一次性还款*/");
////		/*一次性还款*/
////		System.out.println(JSONObject.fromObject(billUpdate("500000", "12", CommonContants.TERM_UNIT_MONTH, "10", StatusContants.PAYMENTMETHOD_STATUS_4.getIndex(), "2016-05-03 00:00:00")));
////		System.out.println("/*一次性还款*/");
//		/**
//		 * @return List<Map<String,String>>( 键值 principal：存储本金 键值 interest：存储利息 键值 benxi：存储本息 键值endDate：还款日期)
//		 * 
//		 */
//	}
//	
//	public static void testMain(){
//		/*按月等额本息*/
//		billUpdate("110000", "60", StatusContants.TERM_UNIT_DAY.getIndex(), "8", StatusContants.PAYMENTMETHOD_STATUS_4.getIndex(), "2016-08-25 00:00:00");
//	}
//
//	public static void tempShow(Map<String, Object> resultMap){
//		LoanSchedule totalLoanSchedule = (LoanSchedule) resultMap.get(SCHEDULE_TOTAL);
//		System.out.println(JSONObject.fromObject(totalLoanSchedule));
////		System.out.println("总额：利息：\t"+ totalLoanSchedule.getInterest()+"\t本金：\t"+totalLoanSchedule.getPrincipal());
//		System.out.println("总额：利息：\t"+ MyNumberUtils.fen2Yuan(totalLoanSchedule.getInterest())
//				+"\t本金：\t"+MyNumberUtils.fen2Yuan(totalLoanSchedule.getPrincipal())
//				+"\t本息：\t"+MyNumberUtils.fen2Yuan(totalLoanSchedule.getPrincipalAndInterest())
//				);
//		List<LoanSchedule> loanSchedules = (List<LoanSchedule>) resultMap.get(SCHEDULE_LIST);
//		for (LoanSchedule bean: loanSchedules){
//			System.out.println(JSONObject.fromObject(bean));
//			System.out.println("期数：\t"+bean.getSeqId()+"\t利息：\t"+MyNumberUtils.fen2Yuan(bean.getInterest())+"\t本金：\t"+MyNumberUtils.fen2Yuan(bean.getPrincipal()));
//		}
//		System.out.println("====");
//	}
//	
//}