package com.zwstudio.tools.services;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Service;

@Service
public class Tool32Service {
	void listFiles(Path path, List<Path> files) throws IOException {
	    try(DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
	        for(Path entry : stream) {
	            if(Files.isDirectory(entry))
	                listFiles(entry, files);
	            if(entry.endsWith(".doc"))
	            	files.add(entry);
	        }
	    }
	}
	
	int countPages(String path) {
		int pageCount = -1;
		try {
			HWPFDocument document = new HWPFDocument(new FileInputStream(path));
//			pageCount = document.getSummaryInformation().getPageCount();
			
			WordExtractor extractor = new WordExtractor(document);
			String[] paragraphs = extractor.getParagraphText();
			for (int i = 0; i < paragraphs.length; ++i)
			    if (paragraphs[i].indexOf("\f") >= 0)
			        ++pageCount;
			extractor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageCount;
	}
	
	public void countDocManual() throws IOException {
		String pathDocManual = "\\\\\\\\IDE-JOMO\\\\ss_server03\\\\project\\\\TW5500\\\\NEC-I標準\\\\10_doc\\\\010_PJ開発\\\\12_オペレーションマニュアル\\\\01最新一式\\\\";
		String v2 = "v2.0\\", v3 = "v3.0_プリカ・ポイント改善対応まで\\";
		
		String path = "\\\\Ide-jomo\\ss_server03\\project\\TW5500\\NEC-I標準\\99_work\\01_personal\\chou\\オペレーションマニュアルv2v3\\";
		String fileIn = "diff.csv";
		String fileOut = "pages.csv";
		List<String> linesIn = Files.readAllLines(Paths.get(path + fileIn), Charset.forName("Shift_JIS"));
		List<String> linesOut = new ArrayList<>();
		List<String[]> linesInfo = 
				linesIn.stream()
				.filter(s -> s != null)
				.map(s -> s.split(","))
				.filter(arr -> arr.length > 2 && !arr[0].startsWith("~$") && arr[0].toLowerCase().endsWith(".doc"))
				.collect(Collectors.toList());
		for(String[] info : linesInfo) {
			String part = info[1] + (!info[1].isEmpty() ? "\\" : "") + info[0];
			int cnt2 = info[2].startsWith("右") ? 0 : countPages(pathDocManual + v2 + part);
			int cnt3 = info[2].startsWith("左") ? 0 : countPages(pathDocManual + v3 + part);
			String line2 = String.format("%s,%s,%d,%d", info[0], info[1], cnt2, cnt3);
			linesOut.add(line2);
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileOut), "SJIS"));
		for(String line2 : linesOut) {
			bw.write(line2);
			bw.newLine();
		}
		bw.close();
	}
}
