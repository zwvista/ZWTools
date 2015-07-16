package com.zwstudio.tools.models;

import static com.zwstudio.tools.models.CsvUtilities.csvField;
import static com.zwstudio.tools.models.CsvUtilities.error;
import lombok.Getter;
import lombok.Setter;

public class Mssyaban {
	//情報テーブル№
	String tblNo() {
		return kykcode02.startsWith("62") ? "2002" :
			kykcode02.startsWith("61") ? "2001" :
			error;
	}
	//車番
	@Getter @Setter
	String syaban;
	//顧客コード
	String kykcode() {
		return kykcode02.replace("-", "");
	}
	//顧客コード（ハイフン付）
	@Getter @Setter
	String kykcode02;
	//顧客名
	@Getter @Setter
	String kykname;
	//カード体系区分
	final String taikeiKbn = "040";
	
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s",
			tblNo(),
			csvField(syaban, 4),
			csvField(kykcode(), 20),
			csvField(kykcode02, 25),
			csvField(kykname, 20),
			csvField(taikeiKbn, 3));
	}
}
