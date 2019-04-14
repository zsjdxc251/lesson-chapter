package com.lesson;

import com.example.common.lang.StringTools;
import org.junit.Test;

import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {


	public static void getName() {

	}

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}


	@Test
	public void testModifier() throws Exception {

		System.out.println(ModifierService.class.getMethod("demo").getDeclaringClass().isInterface());


		System.out.println(StringTools.toBinaryString(Modifier.ABSTRACT));
		System.out.println(StringTools.toBinaryString(Modifier.PUBLIC));
		System.out.println(StringTools.toBinaryString(Modifier.STATIC));
		System.out.println(Modifier.ABSTRACT);
		System.out.println(Modifier.PUBLIC);
		System.out.println(Modifier.STATIC);
		System.out.println(ModifierService.class.getMethod("demo").getModifiers() & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC));


	}
}
