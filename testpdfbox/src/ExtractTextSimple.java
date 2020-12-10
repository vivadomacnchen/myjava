/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//package org.apache.pdfbox.examples.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * This is a simple text extraction example to get started. For more advance usage, see the
 * ExtractTextByArea and the DrawPrintTextLocations examples in this subproject, as well as the
 * ExtractText tool in the tools subproject.
 *
 * @author Tilman Hausherr
 */
public class ExtractTextSimple
{
    private ExtractTextSimple()
    {
        // example class should not be instantiated
    }

    private static ArrayList listpdf(String filepath)
    {
        ArrayList allpdf=new ArrayList();
        File f = new File(filepath);
        // File[] files = f.listFiles();
        if(f.isDirectory())
        {
            for(String f_name:f.list())
            {
                if(f_name.contains(".pdf"))
                    allpdf.add(f_name);
            }
        }
        else
        {
            if(filepath.contains(".pdf"))
                allpdf.add(filepath);
        }
        return allpdf;
    }
    
    private static int rwpdfstring(ArrayList filelist) throws InvalidPasswordException, IOException
    {
    	int ret=0;
//    	int i=0;
    	String rdpdf="";
    	String savedtxt="";
		int row_idx=0;
		try
		{
			@SuppressWarnings("resource")
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFSheet sheet=workbook.createSheet();
			FileOutputStream out = new FileOutputStream("jp_grammer.xls");
			for(int fidx=0;fidx<filelist.size();fidx++)
			{
				rdpdf="jp_grammer_pdf//"+filelist.get(fidx).toString();
				savedtxt=rdpdf.substring(0, rdpdf.indexOf(".pdf")-1)+".txt";
				//
				File txt_if_ext=new File(savedtxt);
				if(txt_if_ext.exists())
				{
					txt_if_ext.delete();
				}
				//
				File inputpdf=new File(rdpdf);
				try (PDDocument document = PDDocument.load(inputpdf))
				{
					AccessPermission ap = document.getCurrentAccessPermission();
					if (!ap.canExtractContent())
					{
						throw new IOException("You do not have permission to extract text");
					}

					PDFTextStripper stripper = new PDFTextStripper();

					// This example uses sorting, but in some cases it is more useful to switch it off,
					// e.g. in some files with columns where the PDF content stream respects the
					// column order.
	//                stripper.setSortByPosition(true);

					for (int p = 1; p <= document.getNumberOfPages(); ++p)
					{
						// Set the page interval to extract. If you don't, then all pages would be extracted.
						stripper.setStartPage(p);
						stripper.setEndPage(p);

						// let the magic happen
						String text = stripper.getText(document);

						// do some nice output with a header
						String pageStr = String.format("page %d:", p);
	//                    System.out.println(pageStr);
						String text2=text.replaceAll(" ", "");
						File saveFile=new File(savedtxt);
						try
						{
						  FileWriter fwriter=new FileWriter(saveFile, true);
						  fwriter.write(text2);
						  fwriter.close();
						}
						catch(Exception e)
						{
						  e.printStackTrace();
						}

						// If the extracted text is empty or gibberish, please try extracting text
						// with Adobe Reader first before asking for help. Also read the FAQ
						// on the website: 
						// https://pdfbox.apache.org/2.0/faq.html#text-extraction
					}
					FileReader fr = new FileReader(savedtxt);
					BufferedReader br = new BufferedReader(fr);
					String strNum;
					String strNum_all="";
					while((strNum=br.readLine())!=null)
					{
						strNum_all+=strNum;
					}//讀第一行
					addsentence(sheet, strNum_all, savedtxt/*, row_idx*/);
				}
			}
			workbook.write(out);
			out.flush();
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	return ret;
    }
    
    private static int addsentence(HSSFSheet sheet_i, String str_i, String rd_file_name)
    {
    	int ret=0;
    	int flag=0;
        int[] markidx=new int[300];
        int mark_step_idx=0;
        int if_endofstring=0;//判斷是否找mark是否超過字串
        int mark_idx_forward=0;//累計mark的位置
        int idx_markstart=0;
        int idx_markstartII=0;//標出下一個標號若沒有下一個標號則此值設定為idx_markstart
        int idx_colstart=0;
        int idx_colend=0;
        int idx_done=0;
        int row_idx=sheet_i.getLastRowNum();
		int dbg_cnt=0;
        Set<Character.UnicodeBlock> japaneseUnicodeBlocks = new HashSet<Character.UnicodeBlock>()
    	{{
    	add(Character.UnicodeBlock.HIRAGANA);
    	add(Character.UnicodeBlock.KATAKANA);
    	}};
    	Set<Character.UnicodeBlock> chineseUnicodeBlocks = new HashSet<Character.UnicodeBlock>()
    	{{
    	add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
    	add(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
    	add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
    	add(Character.UnicodeBlock.GENERAL_PUNCTUATION);
    	add(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
    	add(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
    	add(Character.UnicodeBlock.BASIC_LATIN);
    	}};
    	//
        String jpn_w="";
		String chn_w="";
		String SecndColStr="；；";//第二個column的字串: 即將；；加上jpn_w
    	String str2=str_i.replace("\r","");
        String str3=str2.replace("\n","");
        str3=str3.replace("、", "");
        str3=str3.replace("・", "");
        str3=str3.replace(",", "");
        
		System.out.println("\r\n");	
		System.out.println(rd_file_name);	
		//debug
		//if(rd_file_name=="jp_grammer_pdf//2018_07_11 下午11_39 Office Len.txt")
		//	System.out.println("\r\ntest");	
		//
		//一口氣找出所有標籤位置
		for(int i=0;i<markidx.length;i++)
			markidx[i]=-1;//初始化markidx矩陣
		
		mark_idx_forward=0;//將markidx的指標設定在起頭
		try
		{
			//找①
			mark_step_idx=0;
			if_endofstring=0;
			while(if_endofstring!=1)
			{
				
				mark_step_idx=str3.indexOf("①", mark_step_idx);
				if(mark_step_idx!=-1)
				{
					markidx[mark_idx_forward]=mark_step_idx;
					if((mark_idx_forward+1)<markidx.length)
						mark_idx_forward++;
				}
				else if(mark_step_idx==-1)
				{
					if_endofstring=1;
				}
				
				if((mark_step_idx+1)>str3.length())
				{
					if_endofstring=1;
				}
				else
				{
					mark_step_idx++;
				}
			}
				//
			//找②
			mark_step_idx=0;
			if_endofstring=0;
			while(if_endofstring!=1)
			{
				
				mark_step_idx=str3.indexOf("②", mark_step_idx);
				if(mark_step_idx!=-1)
				{
					markidx[mark_idx_forward]=mark_step_idx;
					if((mark_idx_forward+1)<markidx.length)
						mark_idx_forward++;
				}
				else if(mark_step_idx==-1)
				{
					if_endofstring=1;
				}
				
				if((mark_step_idx+1)>str3.length())
				{
					if_endofstring=1;
				}
				else
				{
					mark_step_idx++;
				}
			}
				//
			//找③
			mark_step_idx=0;
			if_endofstring=0;
			while(if_endofstring!=1)
			{
				
				mark_step_idx=str3.indexOf("③", mark_step_idx);
				if(mark_step_idx!=-1)
				{
					markidx[mark_idx_forward]=mark_step_idx;
					if((mark_idx_forward+1)<markidx.length)
						mark_idx_forward++;
				}
				else if(mark_step_idx==-1)
				{
					if_endofstring=1;
				}
				
				if((mark_step_idx+1)>str3.length())
				{
					if_endofstring=1;
				}
				else
				{
					mark_step_idx++;
				}
			}
				//
			//找④
			mark_step_idx=0;
			if_endofstring=0;
			while(if_endofstring!=1)
			{
				
				mark_step_idx=str3.indexOf("④", mark_step_idx);
				if(mark_step_idx!=-1)
				{
					markidx[mark_idx_forward]=mark_step_idx;
					if((mark_idx_forward+1)<markidx.length)
						mark_idx_forward++;
				}
				else if(mark_step_idx==-1)
				{
					if_endofstring=1;
				}
				
				if((mark_step_idx+1)>str3.length())
				{
					if_endofstring=1;
				}
				else
				{
					mark_step_idx++;
				}
			}
				//
			//找⑤
			mark_step_idx=0;
			if_endofstring=0;
			while(if_endofstring!=1)
			{
				
				mark_step_idx=str3.indexOf("⑤", mark_step_idx);
				if(mark_step_idx!=-1)
				{
					markidx[mark_idx_forward]=mark_step_idx;
					if((mark_idx_forward+1)<markidx.length)
						mark_idx_forward++;
				}
				else if(mark_step_idx==-1)
				{
					if_endofstring=1;
				}
				
				if((mark_step_idx+1)>str3.length())
				{
					if_endofstring=1;
				}
				else
				{
					mark_step_idx++;
				}
			}
				//
			//找⑥
			mark_step_idx=0;
			if_endofstring=0;
			while(if_endofstring!=1)
			{
				
				mark_step_idx=str3.indexOf("⑥", mark_step_idx);
				if(mark_step_idx!=-1)
				{
					markidx[mark_idx_forward]=mark_step_idx;
					if((mark_idx_forward+1)<markidx.length)
						mark_idx_forward++;
				}
				else if(mark_step_idx==-1)
				{
					if_endofstring=1;
				}
				
				if((mark_step_idx+1)>str3.length())
				{
					if_endofstring=1;
				}
				else
				{
					mark_step_idx++;
				}
			}
				//
			Arrays.sort(markidx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//依序找出每段的起頭並下一個mark位置作為此段結尾
		try
		{
			String jpsentence="";
			int idx_chnword_s=0;//中文字串開始
			int idx_chnword_e=0;//中文字串結束
			int st=0;//狀態圖之狀態
			int threshold_cnt=0;//累計漢字數
			for(int i=0;i<markidx.length;i++)
			{
				if(markidx[i]>=0)
				{
					jpsentence="";
					jpn_w="";
					chn_w="";
					SecndColStr="；；";
					st=0;
					idx_chnword_s=0;
					idx_chnword_e=0;
					threshold_cnt=0;
					idx_markstart=0;//日文字串的起頭
					if(i!=(markidx.length-1))
					{
						if(((markidx[i]+1)<str3.length())&&((markidx[i+1]-1)>=0))
							jpsentence=str3.substring(markidx[i]+1,markidx[i+1]);
					}
					else if(i==(markidx.length-1))
					{
						if(((markidx[i]+1)<str3.length())&&(str3.length()>=0))
							jpsentence=str3.substring(markidx[i]+1,str3.length());
					}
					//
					//先以左右括號定義為中文字串if(左括號大於0且右括號大於0且右括號大於左括號)=>就直接定義出中文字串再定義出日文字串
					//其餘的情況走下方=>此情形仍出現判讀錯誤的中文字串使得範圍設定為-1時，則將整個字串定義為日文字串
					idx_chnword_s=jpsentence.indexOf("(", 0);
					idx_chnword_e=jpsentence.indexOf(")", 0);
					if((idx_chnword_s>0)&&(idx_chnword_e>0)&&(idx_chnword_e>idx_chnword_s))
					{
						jpn_w=jpsentence.substring(0,idx_chnword_s-1);
						chn_w=jpsentence.substring(idx_chnword_s+1,idx_chnword_e-1);
						jpn_w=jpn_w.replace("。", "");
						chn_w=chn_w.replace("。", "");
						jpn_w=jpn_w.replace("、", "");
						jpn_w=jpn_w.replace("(", "");
						chn_w=chn_w.replace("(", "");
						jpn_w=jpn_w.replace(")", "");
						chn_w=chn_w.replace(")", "");
					  //idx_done+=d;
					  //st=3;
					  try
					  {
						  HSSFRow nrow=sheet_i.createRow(row_idx);
						  HSSFCell ncell=nrow.createCell(0);
						  ncell.setCellValue(jpn_w);
						  ncell=nrow.createCell(1);
						  SecndColStr=SecndColStr+jpn_w;
						  ncell.setCellValue(SecndColStr);
						  ncell=nrow.createCell(2);
						  ncell.setCellValue(chn_w);
					  }
					  catch(Exception e)
					  {
						  e.printStackTrace();
					  }
					  row_idx++;
					}
					else
					{
						idx_chnword_s=0;
						idx_chnword_e=0;
						for(int d=0;d<jpsentence.length()/*idx_check*/;d++)// (char c : jpsentence.toCharArray())
						{
						  //System.out.println(st);
						  //check character type
						  //System.out.println("\r\n");
						  //System.out.println(d);
						  //System.out.println(jpsentence.charAt(d));	
						  //System.out.println(Character.UnicodeBlock.of(jpsentence.charAt(d)));
						  //首次讀到中文字形時進入st=1
						  if ((st==0)&&(chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(jpsentence.charAt(d)))))
						  {
							st=1; 
							threshold_cnt++;  
							idx_chnword_s=d;
						  }
						  //若讀到最後一個字元仍維持到st=0 或1表示沒有讀到連續的7個中文字元 表示僅有日文字元 此時應將
						  else if(((st==0)||(st==1))&&(d==(jpsentence.length()-1)))
						  {
							  //jpn_w=jpsentence.substring(idx_markstart,d);
							  //idx_done+=d;
							  try
							  {
								  jpn_w=jpsentence.substring(idx_markstart,d);
								  //idx_done+=d;
								  HSSFRow nrow=sheet_i.createRow(row_idx);
								  HSSFCell ncell=nrow.createCell(0);
								  ncell.setCellValue(jpn_w);
								  ncell=nrow.createCell(1);
								  SecndColStr=SecndColStr+jpn_w;
								  ncell.setCellValue(SecndColStr);
								  ncell=nrow.createCell(2);
								  ncell.setCellValue(chn_w);
							  }
							  catch(Exception e)
							  {
								  e.printStackTrace();
							  }
						  }
						  else if((st==1)&&(chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(jpsentence.charAt(d)))))
						  {
							  threshold_cnt++;
							  if(threshold_cnt==8)
							  {
								  st=2;
							  }
						  }
						  else if((st==1)&&!(chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(jpsentence.charAt(d)))))
						  {
							st=0;
							threshold_cnt=0;
							idx_chnword_s=0;
						  }
						  else if(st==2)
						  {
							  if(!(chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(jpsentence.charAt(d)))))
							  {
								  try
								  {
									  //idx_chnword_e=d;
									  //System.out.println("idx_markstart=");
									  //System.out.println(idx_markstart);
									  //System.out.println("idx_chnword_s=");
									  //System.out.println(idx_chnword_s);
									  //System.out.println("idx_chnword_e=");
									  //System.out.println(idx_chnword_e);
									  if((idx_chnword_s-1)>=0)
									  {  
										jpn_w=jpsentence.substring(0,idx_chnword_s-1);
										chn_w=jpsentence.substring(idx_chnword_s,d-1);
									  }
									  else if((idx_chnword_s-1)<0)
									  {
										  System.out.println("\r\nCan't Distinguish japanese and chinese sentense\r\n");
										  jpn_w=jpsentence;
									  }
									  jpn_w=jpn_w.replace("。", "");
									  chn_w=chn_w.replace("。", "");
									  jpn_w=jpn_w.replace("、", "");
									  jpn_w=jpn_w.replace("(", "");
									  chn_w=chn_w.replace("(", "");
									  jpn_w=jpn_w.replace(")", "");
									  chn_w=chn_w.replace(")", "");
									  //idx_done+=d;
									  //st=3;
									  try
									  {
										  HSSFRow nrow=sheet_i.createRow(row_idx);
										  HSSFCell ncell=nrow.createCell(0);
										  ncell.setCellValue(jpn_w);
										  ncell=nrow.createCell(1);
										  SecndColStr=SecndColStr+jpn_w;
										  ncell.setCellValue(SecndColStr);
										  ncell=nrow.createCell(2);
										  ncell.setCellValue(chn_w);
									  }
									  catch(Exception e)
									  {
										  e.printStackTrace();
									  }
									  row_idx++;
									  break;
								  }
								  catch(Exception e)
								  {
									  e.printStackTrace();
								  }
							  }
							  
							  if(d==(jpsentence.length()-1))
							  {
								if((idx_chnword_s-1)>=0)
								{  
								jpn_w=jpsentence.substring(0,idx_chnword_s-1);
								chn_w=jpsentence.substring(idx_chnword_s,d-1);
								}
								else if((idx_chnword_s-1)<0)
								{
								  System.out.println("\r\nCan't Distinguish japanese and chinese sentense\r\n");
								  jpn_w=jpsentence;
								}
								jpn_w=jpn_w.replace("。", "");
								chn_w=chn_w.replace("。", "");
								jpn_w=jpn_w.replace("、", "");
								jpn_w=jpn_w.replace("(", "");
								chn_w=chn_w.replace("(", "");
								jpn_w=jpn_w.replace(")", "");
								chn_w=chn_w.replace(")", "");
								//idx_done+=d;
								//st=3;
								try
								{
								  HSSFRow nrow=sheet_i.createRow(row_idx);
								  HSSFCell ncell=nrow.createCell(0);
								  ncell.setCellValue(jpn_w);
								  ncell=nrow.createCell(1);
								  SecndColStr=SecndColStr+jpn_w;
								  ncell.setCellValue(SecndColStr);
								  ncell=nrow.createCell(2);
								  ncell.setCellValue(chn_w);
								}
								catch(Exception e)
								{
								  e.printStackTrace();
								}
								row_idx++;
								break;  
							  }
							  
							  if(jpsentence.charAt(d)==')')
							  {
								  try
								  {
									  idx_chnword_e=d;
									  jpn_w=jpsentence.substring(0,idx_chnword_s-1);
									  chn_w=jpsentence.substring(idx_chnword_s,idx_chnword_e-1);
									  jpn_w=jpn_w.replace("。", "");
									  chn_w=chn_w.replace("。", "");
									  jpn_w=jpn_w.replace("、", "");
									  jpn_w=jpn_w.replace("(", "");
									  chn_w=chn_w.replace("(", "");
									  jpn_w=jpn_w.replace(")", "");
									  chn_w=chn_w.replace(")", "");
									  //idx_done+=d;
									  //st=3;
									  try
									  {
										  HSSFRow nrow=sheet_i.createRow(row_idx);
										  HSSFCell ncell=nrow.createCell(0);
										  ncell.setCellValue(jpn_w);
										  ncell=nrow.createCell(1);
										  SecndColStr=SecndColStr+jpn_w;
										  ncell.setCellValue(SecndColStr);
										  ncell=nrow.createCell(2);
										  ncell.setCellValue(chn_w);
									  }
									  catch(Exception e)
									  {
										  e.printStackTrace();
									  }
									  row_idx++;
									  break;
								  }
								  catch(Exception e)
								  {
									  e.printStackTrace();
								  }
							  }
							  
						  }
						}
					}
					//
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//
    	return ret;
    }
    /**
     * This will print the documents text page by page.
     *
     * @param args The command line arguments.
     *
     * @throws IOException If there is an error parsing or extracting the document.
     */
    public static void main(String[] args) throws IOException
    {
//        if (args.length != 1)
//        {
//            usage();
//        }
    	// write your code here
    	ArrayList jppdf=listpdf("jp_grammer_pdf");
    	rwpdfstring(jppdf);
        return;
    }

    /**
     * This will print the usage for this document.
     */
    private static void usage()
    {
        System.err.println("Usage: java " + ExtractTextSimple.class.getName() + " <input-pdf>");
        System.exit(-1);
    }
}