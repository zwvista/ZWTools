package com.zwstudio.tools.services;

import static com.zwstudio.tools.models.CsvUtilities.trimSpace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.zwstudio.tools.models.Mskana;
import com.zwstudio.tools.models.Mssyaban;

@Service
public class Tool31Service {
	
	private Pattern[] ptnsMssyaban = new Pattern[] {
		Pattern.compile("車番\\s+(.+)"),
		Pattern.compile("顧客名\\s+(.+)"),
		Pattern.compile("顧客ｺｰﾄﾞ\\s+(.+)")
	};
	private Pattern[] ptnsMskana = new Pattern[] {
		Pattern.compile("^顧客コード\\s+(.+)"),
		Pattern.compile("^顧客名 \\(サーチ用\\)(.+)"),
		Pattern.compile("^顧客名  \\s+(.+)")
	};
	private String path = "\\\\Ide-jomo\\ss_server03\\project\\TW5500\\NEC-I標準\\99_work\\01_personal\\chou\\藤井商事_マスター移行\\";
	
	private void convertMssyaban(BufferedReader br, List<String> linesOut) throws IOException {
		Mssyaban record = new Mssyaban();
		String line = null;
		while((line = br.readLine()) != null) {
			for(int i = 0; i < 3; i++) {
				Matcher m = ptnsMssyaban[i].matcher(line);
				if(!m.find()) continue;
				String s = trimSpace(m.group(1));
				if(i == 0) {
					record.setSyaban(s);
					break;
				}
				if(i == 1) {
					record.setKykname(trimSpace(s.replace('\uFFFD', '　')));
					break;
				}
				record.setKykcode02(s);
				linesOut.add(record.toString());
			}
		}
	}
	
	private void convertMskana(BufferedReader br, List<String> linesOut) throws IOException {
		Mskana record = new Mskana();
		String line = null;
		while((line = br.readLine()) != null) {
			for(int i = 0; i < 3; i++) {
				Matcher m = ptnsMskana[i].matcher(line);
				if(!m.find()) continue;
				String s = trimSpace(m.group(1));
				if(i == 0) {
					record.setKykcode02(s);
					break;
				}
				if(i == 1) {
					record.setKanaNm(trimSpace(s.replace('\uFFFD', '　')));
					break;
				}
				record.setKanjiNm(trimSpace(s.replace('\uFFFD', '　')));
				String t = record.toString();
				System.out.println(t);
				linesOut.add(t);
			}
		}
	}

	public void convertTest() throws IOException {
		String fileIn = "テスト\\04-車番サーチマスタ.txt";
		String fileOut = "テスト\\mssyaban.dat";
		List<String> linesOut = new ArrayList<>();
		
//		linesIn = Files.readAllLines(pathIn, Charset.defaultCharset());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileIn), "SJIS"));
		convertMssyaban(br, linesOut);
		br.close();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileOut), "SJIS"));
		for(String line : linesOut) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

	public void convertHonban() throws IOException {
		String fileIn1 = "本番\\車番サーチ.DAT";
		String fileIn2 = "本番\\カナサーチ.DAT";
		String fileOut1 = "本番\\mssyaban.dat";
		String fileOut2 = "本番\\msskana.dat";
		List<String> linesOut = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileIn1), "SJIS"));
		convertMssyaban(br, linesOut);
		br.close();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileOut1), "SJIS"));
		for(String line : linesOut) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();

		linesOut = new ArrayList<>();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileIn2), "SJIS"));
		convertMskana(br, linesOut);
		br.close();
		
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileOut2), "SJIS"));
		for(String line : linesOut) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

}
