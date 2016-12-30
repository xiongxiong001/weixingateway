package org.demoexm.core.util;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



/**
 * PDF工具转换类 通过itext生成pdf文件。
 * 
 * @author chenweixian
 *
 */
public class PdfUtils {
	/**
	 * Creates a PDF with the words "Hello World"
	 * 
	 * @param file
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void createPdf(String htmlFile, String pdfFile)
			throws IOException, DocumentException {
		Document document = new Document(PageSize.A4, 80, 79, 20, 45);
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
		Font FontChinese = new Font(bfChinese, 14, Font.BOLD);
		Font BoldChinese = new Font(bfChinese, 14, Font.BOLD);
		Font titleChinese = new Font(bfChinese, 20, Font.BOLD);
		Font subFontChinese = new Font(bfChinese, 12, Font.ITALIC); 
		Font moneyFontChinese = new Font(bfChinese, 8, Font.ITALIC); 
		Font subBoldFontChinese = new Font(bfChinese, 8, Font.BOLD); 
		PdfWriter.getInstance(document,new FileOutputStream(
								"d:/1.pdf"));
		document.open(); // 打开文档
		// ------------开始写数据-------------------
		Paragraph title = new Paragraph("起租通知书", titleChinese);// 抬头
		title.setAlignment(Element.ALIGN_CENTER); // 居中设置
		title.setLeading(1f);// 设置行间距//设置上面空白宽度
		document.add(title);

		title = new Paragraph("致：XXX公司", BoldChinese);// 抬头
		title.setSpacingBefore(25f);// 设置上面空白宽度
		document.add(title);

		title = new Paragraph(
				"         贵我双方签署的编号为 XXX有关起租条件已满足，现将租赁合同项下相关租赁要素明示如下：",
				FontChinese);
		title.setLeading(22f);// 设置行间距
		document.add(title);

		float[] widths = { 8f, 15f, 15f, 15f};// 设置表格的列宽和列数 默认是4列
//		if (depositBean.isExpress() == 5) { // 如果是明示就是6列
//			widths = new float[] { 8f, 15f, 19f, 19f, 19f, 20f };
//		} else if (depositBean.isExpress() == 6) { // 如果是业发事业部就是7列
//			widths = new float[] { 8f, 15f, 15f, 15f, 15f, 16f, 16f };
//		}

		PdfPTable table = new PdfPTable(widths);// 建立一个pdf表格
		table.setSpacingBefore(20f);// 设置表格上面空白宽度
		table.setTotalWidth(500);// 设置表格的宽度
		table.setWidthPercentage(100);// 设置表格宽度为%100
		// table.getDefaultCell().setBorder(0);//设置表格默认为无边框

		String[] tempValue = new String[] { "2011-07-07", "2522","11.11", "2011-07-08", "2122","11.12","2011-07-08", "2222","11.13" }; // 租金期次列表
		
		PdfPCell cell = null;
		// ---表头
		cell = new PdfPCell(new Paragraph("期次", subBoldFontChinese));// 描述
		cell.setFixedHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("租金日", subBoldFontChinese));// 描述
		cell.setFixedHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("各期租金金额", subBoldFontChinese));// 描述
		cell.setFixedHeight(10);
		
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		table.addCell(cell);

		Image upgif = Image
				.getInstance("D:/stamp.png");
//		upgif.scalePercent(7.5f);// 设置缩放的百分比%7.5
		upgif.scaleAbsolute(30, 30);
		upgif.setAlignment(Image.UNDERLYING);
		upgif.setIndentationLeft(20);
		
		cell = new PdfPCell(upgif);// 描述
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_LEFT); // 设置垂直居中
//		cell.setPaddingTop(10);
//		cell.setPaddingBottom(10);
//		cell.setPaddingLeft(10);
		cell.setPadding(10);
		cell.setBorderWidth(5);
		
//		cell.setFixedHeight(20);
//		cell.addElement(upgif);
		table.addCell(cell);
		
		
		
		int rowCount = 1; // 行计数器
		int argument= 3;
		for (int j = 0; j < tempValue.length; j++) {
			
			if (j%argument == 0) { // 第一列 日期
				cell = new PdfPCell(new Paragraph(rowCount + "",
						moneyFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				rowCount++;
			}
			
			cell = new PdfPCell(new Paragraph(tempValue[j], moneyFontChinese));// 描述
			cell.setFixedHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			table.addCell(cell);
		}
		document.add(table);

		title = new Paragraph("                租金总额：XXX", FontChinese);
		title.setLeading(22f);// 设置行间距
		document.add(title);
		title = new Paragraph("         特此通知！", FontChinese);
		title.setLeading(22f);// 设置行间距
		document.add(title);
		// -------此处增加图片和日期，因为图片会遇到跨页的问题，图片跨页，图片下方的日期就会脱离图片下方会放到上一页。
		// 所以必须用表格加以固定的技巧来实现
		float[] widthes = { 50f };// 设置表格的列宽和列数
		PdfPTable hiddenTable = new PdfPTable(widthes);// 建立一个pdf表格
		hiddenTable.setSpacingBefore(11f); // 设置表格上空间
		hiddenTable.setTotalWidth(500);// 设置表格的宽度
		hiddenTable.setWidthPercentage(100);// 设置表格宽度为%100
		hiddenTable.getDefaultCell().disableBorderSide(1);
		hiddenTable.getDefaultCell().disableBorderSide(2);
		hiddenTable.getDefaultCell().disableBorderSide(4);
		hiddenTable.getDefaultCell().disableBorderSide(8);

		
		
		Paragraph paragraph = new Paragraph(" 1", FontChinese);
		paragraph.add(upgif);
		document.add(paragraph);
		cell = new PdfPCell(paragraph);// 描述
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);// 设置内容水平居中显示
//		cell.addElement(upgif);
		cell.setPaddingTop(0f); // 设置内容靠上位置
		cell.setPaddingBottom(0f);
		cell.setPaddingRight(20f);
		cell.setBorder(Rectangle.NO_BORDER);// 设置单元格无边框
		hiddenTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("XX 年 XX 月 XX 日                    ",
				FontChinese));// 金额
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);// 设置内容水平居中显示
		cell.setPaddingTop(0f);
		cell.setPaddingRight(20f);
		cell.setBorder(Rectangle.NO_BORDER);
		hiddenTable.addCell(cell);
		document.add(hiddenTable);
		document.close();
	}

	/**
	 * Main method
	 */
	public static void main(String[] args) throws IOException,
			DocumentException {

		String htmlFile = "D:/aa.html";
		String pdfFile = "D:/aa.pdf";
		// File file = new File(DEST);
		// file.getParentFile().mkdirs();
		PdfUtils.createPdf(htmlFile, pdfFile);
		System.out.println("ok...........");
	}

}
