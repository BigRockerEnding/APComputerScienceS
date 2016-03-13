package org.jointheleague.stephenh.TwoDExerciseS;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoDTesterS {

	TwoDCrazyS src = new TwoDCrazyS();

	@Test
	public void simpleTest2D() {
		int[][] array = src.holey2DIntArray(115, 75);
		assertEquals("int[][]", array.getClass().getSimpleName());
		assertEquals("[[I", array.getClass().getName());
		System.out.println(array.length);
	}

	@Test
	public void testEquals() {
		int[][] a = new int[][] { { 1, 2, 3, 4, 5 }, { 1, 2, 3 } };
		int[][] b = new int[][] { { 1, 2, 3 }, { 1, 2, 3, 4, 5 } };

	}
}
