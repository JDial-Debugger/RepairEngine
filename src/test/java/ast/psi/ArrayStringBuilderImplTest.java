package ast.psi;

import ast.interfaces.TypeDelegate;
import ast.psi.factory.ArrayStringBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArrayStringBuilderImplTest {

	private ArrayStringBuilderImpl builderToTest;
	private TypeDelegate mockTypeDelegate;

	@BeforeEach
	public void Setup() {
		this.builderToTest = new ArrayStringBuilderImpl();
		this.mockTypeDelegate = mock(TypeDelegate.class);
		when(this.mockTypeDelegate.toString()).thenReturn("int");
	}

	@Test
	void buildArrayInitStringOnManyDimensions() {
		String name = "var1";
		String initValue = "0";
		Integer[] dimensions = { 1, 3, 2 };

		String expectedText = "int[][][] var1 = {{{0, 0}, {0, 0}, {0, 0}}}";

		String result = this.builderToTest.buildArrayDeclarationStatement(this.mockTypeDelegate,
				name,
				initValue,
				dimensions);
		assertEquals(expectedText, result);
	}

	@Test
	void buildArrayInitStringOnSingleDimension() {
		String name = "var1";
		String initValue = "0";
		Integer[] dimensions = { 4 };

		String expectedText = "int[] var1 = {0, 0, 0, 0}";

		String result = this.builderToTest.buildArrayDeclarationStatement(this.mockTypeDelegate,
				name,
				initValue,
				dimensions);
		assertEquals(expectedText, result);
	}

	@Test
	void buildArrayInitStringOnNestedZeroDimension() {
		String name = "var1";
		String initValue = "0";
		Integer[] dimensions = { 3, 2, 0 };

		String expectedText = "int[][][] var1 = {{{}, {}}, {{}, {}}, {{}, {}}}";

		String result = this.builderToTest.buildArrayDeclarationStatement(this.mockTypeDelegate,
				name,
				initValue,
				dimensions);
		assertEquals(expectedText, result);
	}

	@Test
	void buildArrayInitStringOnEmptyDimensions() {
		String name = "var1";
		String initValue = "0";
		Integer[] dimensions = { };

		String expectedText = "int[] var1 = {}";

		String result = this.builderToTest.buildArrayDeclarationStatement(this.mockTypeDelegate,
				name,
				initValue,
				dimensions);
		assertEquals(expectedText, result);
	}

	@Test
	void buildArrayInitStringOnBadDimension() {
		String name = "var1";
		String initValue = "0";
		Integer[] dimensions = { 3, 2, 0, 4 };

		String expectedErrorMessage = "Invalid dimension size 0 provided on dimensions[2]";
		InvalidDimensionSizeException ex = assertThrows(InvalidDimensionSizeException.class,
				() -> this.builderToTest.buildArrayDeclarationStatement(this.mockTypeDelegate,
						name,
						initValue,
						dimensions));
		assertEquals(expectedErrorMessage, ex.getMessage());
	}
}