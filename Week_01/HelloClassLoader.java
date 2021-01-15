package time.geekbang.org;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * Homework 1: ClassLoader
 * @author jixch
 *
 */
public class HelloClassLoader extends ClassLoader {

	public static void main(String[] args) {
		try {
			Object hello = new HelloClassLoader().findClass("Hello").newInstance();
			Method[] methods = hello.getClass().getDeclaredMethods();
			for (Method method : methods) {
				//invoke hello method
				method.invoke(hello);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String helloBase64 = encode("src\\time\\geekbang\\org\\Hello.xlass");
		byte[] bytes = decode(helloBase64);
		byte[] newBytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			newBytes[i] = (byte) (255 - bytes[i]);
		}

		return defineClass(name, newBytes, 0, newBytes.length);
	}

	/**
	 * base64 decrypt
	 * @param base64
	 * @return
	 */
	public byte[] decode(String base64) {
		return Base64.getDecoder().decode(base64);
	}

	/**
	 * base64 encrypt
	 * @param path
	 * @return
	 */
	public String encode(String path) {
		try {
			File file = new File(path);
			FileInputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			inputStream.read(buffer);
			inputStream.close();
			return Base64.getEncoder().encodeToString(buffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ok";
	}

}
