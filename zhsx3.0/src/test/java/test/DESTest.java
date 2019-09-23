package test;

import com.zzrenfeng.zhsx.util.DESUtils;

public class DESTest {

	public void test0() {

		System.out.println("========test=======");

	}
	
	public void DESTest0() {
		String name = "root";
		String password = "123456";
		String encryname = DESUtils.getEncryptString(name);
		String encrypassword = DESUtils.getEncryptString(password);

		System.out.println("============密文================");
		System.out.println(encryname);
		System.out.println(encrypassword);

		String decryptString1 = DESUtils.getDecryptString(encryname);
		String decryptString2 = DESUtils.getDecryptString(encrypassword);
		System.out.println("============明文================");
		System.out.println(decryptString1);
		System.out.println(decryptString2);
	}
}
