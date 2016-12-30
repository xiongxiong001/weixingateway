package org.demoexm.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.demoexm.core.contants.ControllerContants;

/**   
 * CSV操作(导出和导入)
 *
 * @version 1.0 Jan 27, 2014 4:30:58 PM   
 */
public class CSVUtils {
    /**
     * 导出
     * 
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        BufferedReader br=null;
        try {
           // br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
            
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out,"gbk");
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }
    
    /**
     * 导入
     * 
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file){
        List<String> dataList=new ArrayList<String>();
        
        BufferedReader br=null;
        try { 
            br = new BufferedReader(new FileReader(file));
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                dataList.add(line);
            }
        }catch (Exception e) {
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return dataList;
    }
    
    /**导出文件
     * 
     */
    public static void exportResponse(String fullFileName, List<String> rows, HttpServletResponse response) throws IOException{
   	 	File file=new File(fullFileName);
        boolean isSuccess = exportCsv(file, rows);
  	    System.out.println("导出保存到本地结果：" + isSuccess);
          
        OutputStream o = response.getOutputStream();  
  		byte b[] = new byte[1024];  
  		File fileLoad = new File(fullFileName);  
  		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileLoad.getName(),ControllerContants.CHARSET_UTF8));
  		response.setContentType("application/csv");  // set the MIME type.   
  		response.setHeader("Content_Length", String.valueOf(fileLoad.length())); // get the file length.
  		FileInputStream in = new FileInputStream(fileLoad);  // download the file.  
  		int n = 0;  
  		while ((n = in.read(b)) != -1) {  
  		    o.write(b, 0, n);  
  		}
   }

	@SuppressWarnings("static-access")
	public static void genCsvFile(String pathName, String fileName, String string){
		if (StringUtils.isEmpty(string)) {
			return;
		}
		File file = new File(pathName);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(pathName + file.separator + fileName);
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		// BufferedReader br=null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, "gbk");
			bw = new BufferedWriter(osw);
			bw.append(string);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    public static void main(String[] args) {
    	try {
			genCsvFile("d:/temp/csv", "test.csv", "`2015-08-14 21:21:04,`wxb0006f9e9a661ba0,`10017589,`10023930,`1162516190,`1010230821201508140622488032,`11625201508140002716,`oyKe-jrJRKu7YCZHyrWiLlkNspn8,`MICROPAY,`SUCCESS,`CFT,`CNY,`21.00,`0.00,`0,`0,`0,`0,`,`,`微信刷卡支付,`绿景香颂店-深圳,`0.06000,`0.30%");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}