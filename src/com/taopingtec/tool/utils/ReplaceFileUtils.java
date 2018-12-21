package com.taopingtec.tool.utils;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.taopingtec.tool.Constants;
import com.taopingtec.tool.ReplaceCfg;

public class ReplaceFileUtils {
	public static ArrayList<ReplaceCfg> getReplaceCfgs(String strRFilePath) throws IOException {
		ArrayList<ReplaceCfg> replaceCfgList = new ArrayList<ReplaceCfg>();

		InputStreamReader read = new InputStreamReader(new FileInputStream(new File(strRFilePath)));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if (!lineTxt.contains(Constants.REPLACE_SEG_TAG))
				continue;

			ReplaceCfg replaceCfg = getReplaceCfg(lineTxt);
			if (null != replaceCfg)
				replaceCfgList.add(replaceCfg);
		}
		read.close();

		return replaceCfgList;
	}

	public static ReplaceCfg getReplaceCfg(String lineTxt) {
		String[] subStrs = lineTxt.split(Constants.REPLACE_SEG_TAG);

		if (null == subStrs || 2 != subStrs.length)
			return null;

		ReplaceCfg replaceCfg = new ReplaceCfg();
		replaceCfg.strSrcContent = subStrs[0].trim();
		replaceCfg.strDstContent = subStrs[1].trim();

		System.out.println("src:" + replaceCfg.strSrcContent + ",dst:" + replaceCfg.strDstContent);

		return replaceCfg;
	}

	public static void replaceContentByFile(ArrayList<ReplaceCfg> replaceCfgList, File file)
			throws IOException {
		if (null == replaceCfgList || replaceCfgList.size() <= 0 || null == file || !file.exists()
				|| !file.isFile())
			return;

		InputStreamReader read = new InputStreamReader(new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(read);
		CharArrayWriter tempStream = new CharArrayWriter();
		String lineTxt = null;
		String newLineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			newLineTxt = lineTxt;

			for (ReplaceCfg replaceCfg : replaceCfgList) {
				if (null == replaceCfg || null == replaceCfg.strSrcContent
						|| null == replaceCfg.strDstContent
						|| !newLineTxt.contains(replaceCfg.strSrcContent))
					continue;

				newLineTxt = newLineTxt.replace(replaceCfg.strSrcContent, replaceCfg.strDstContent);
			}

			tempStream.append(newLineTxt);
			tempStream.append(System.getProperty("line.separator"));
		}

		bufferedReader.close();
		FileWriter out = new FileWriter(file);
		tempStream.writeTo(out);
		out.close();
	}

}
