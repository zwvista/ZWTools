package com.zwstudio.tools.models;

import static com.zwstudio.tools.models.CsvUtilities.csvField;
import static com.zwstudio.tools.models.CsvUtilities.error;
import lombok.Getter;
import lombok.Setter;

public class Mskana {
	//単価ランク
	final String rank = "00";
	//添付区分
	final String tenpKbn = "0";
	//情報テーブル№
	String tblNo() {
		return kykcode02.startsWith("62") ? "2002" :
			kykcode02.startsWith("61") ? "2001" :
			error;
	}
	//顧客コード
	String kykcode() {
		return kykcode02.replace("-", "");
	}
	//顧客コード（ハイフン付）
	@Getter @Setter
	String kykcode02;
	//顧客名（カナ）
	@Getter @Setter
	String kanaNm;
	//顧客名（漢字)
	@Getter @Setter
	String kanjiNm;
	//カード体系区分
	final String taikeiKbn = "040";
	
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
			rank,
			tenpKbn,
			tblNo(),
			csvField(kykcode(), 20),
			csvField(kykcode02, 25),
			csvField(kanaNm, 20),
			csvField(kanjiNm, 20),
			csvField(taikeiKbn, 3));
	}
}
