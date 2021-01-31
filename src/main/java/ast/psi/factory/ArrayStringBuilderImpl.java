package ast.psi.factory;

import ast.interfaces.Type;
import ast.psi.InvalidDimensionSizeException;

public class ArrayStringBuilderImpl implements ArrayStringBuilder {

	private Integer[] dimensions;

	public ArrayStringBuilderImpl() {
	}

	/**
	 *
	 * @param type
	 * @param name
	 * @param initValue
	 * @param dimensions
	 * @throws
	 * @return
	 */
	@Override
	public String buildArrayDeclarationStatement(
			Type type, String name, String initValue, Integer[] dimensions) {
		this.dimensions = dimensions;
		String declarationText = this.buildArrayDeclString(type, name);
		String initializationText = this.buildArrayInitString(initValue);
		return declarationText + " = " + initializationText;
	}

	//  example: "int a[][][]"
	private String buildArrayDeclString(Type type, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(type.toString());
		this.appendBrackets(sb);
		sb.append(" ");
		sb.append(name);
		return sb.toString();
	}

	private void appendBrackets(StringBuilder stringBuilder) {
		stringBuilder.append("[]");
		for (int i = 1; i < dimensions.length; ++i) {
			stringBuilder.append("[]");
		}
	}

	//  example: {{{0, 0}, {0, 0}, {0, 0}}
	private String buildArrayInitString(String initValue) {
		if (dimensions.length == 0) {
			return "{}";
		}

		StringBuilder emptyBuilder = new StringBuilder();
		StringBuilder result = this.buildText(emptyBuilder, initValue, 0);
		return result.toString();
	}

	private StringBuilder buildText(
			StringBuilder text, String initValue, int dimensionsIndex) {

		int curSize = this.dimensions[dimensionsIndex];
		if (dimensionsIndex == dimensions.length - 1) {
			return buildBaseText(text, initValue, curSize);
		}
		if (curSize == 0) {
			throw new InvalidDimensionSizeException(0, dimensionsIndex);
		}
		text.append("{");
		for (int i = 0; i < curSize; ++i) {
			this.buildText(text, initValue, dimensionsIndex + 1);
			if (i < curSize - 1) {
				text.append(", ");
			}
		}
		text.append("}");
		return text;
	}

	private StringBuilder buildBaseText(StringBuilder text, String initValue, int size) {
		text.append("{");
		for (int i = 0; i < size; ++i) {
			text.append(initValue);
			if (i < size - 1) {
				text.append(", ");
			}
		}
		text.append("}");
		return text;
	}
}
