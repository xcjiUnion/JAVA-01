package org.geekbang.time.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GeneratorData {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();

		String fileName = "C:\\Projects\\JavaTrain\\JAVA-01\\Week_07\\t_order.txt";
		Path path = Paths.get(fileName);

		String dateTime = "2021-03-28 22:50:00";
		for (int i = 0; i < 1000000; i++) {
			// 订单号
			sb.append("ordernumer_" + (i + 1));
			sb.append("\t");
			// 金额
			sb.append(10000 + i);
			sb.append("\t");
			// 状态
			if (i % 5 == 1) {
				sb.append("1");
			} else if (i % 5 == 2) {
				sb.append("2");
			} else if (i % 5 == 3) {
				sb.append("3");
			} else if (i % 5 == 4) {
				sb.append("4");
			} else {
				sb.append("5");
			}
			sb.append("\t");

			// 时间
			sb.append(dateTime);
			sb.append("\t");

			sb.append(dateTime);
			sb.append("\t");

			sb.append("\n");

			// 每10000次写入一次文件
			if ((i + 1) % 10000 == 0) {
				try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
						StandardOpenOption.APPEND)) {
					writer.write(sb.toString());
					sb = new StringBuilder();
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("Time take: " + (end - start));

	}

}
