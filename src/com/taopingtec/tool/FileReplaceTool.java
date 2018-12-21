package com.taopingtec.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.taopingtec.tool.utils.FileUtils;
import com.taopingtec.tool.utils.ReplaceFileUtils;

public class FileReplaceTool {

	public static void main(String[] args) throws IOException {
		if (null == args || 2 != args.length) {
			System.out
					.println("Please use like this: java -jar FileReplaceTool.jar replace.cfg ./replaceDir");
			return;
		}

		replaceFiles(args[0], args[1]);

		System.out.println("Finished");

	}

	public static void replaceFiles(String strCfgFilePath, String strReplaceDir) throws IOException {
		ArrayList<ReplaceCfg> replaceCfgList = ReplaceFileUtils.getReplaceCfgs(strCfgFilePath);

		if (null == replaceCfgList || replaceCfgList.size() <= 0) {
			System.out.println("replaceCfgList is Empty, Please Check the file:" + strCfgFilePath);
			return;
		}

		System.out.println("replaceCfgList size: " + replaceCfgList.size());

		// 获取文件列表
		ArrayList<File> fileList = FileUtils.getFileList(strReplaceDir);
		System.out.println("File Count: " + fileList.size());
		for (File file : fileList) {
			System.out.println(file.getPath());
			ReplaceFileUtils.replaceContentByFile(replaceCfgList, file);
		}
	}

}
