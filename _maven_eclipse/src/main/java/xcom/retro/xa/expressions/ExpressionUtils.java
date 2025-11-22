

package xcom.retro.xa.expressions ;


import java.nio.ByteBuffer ;

import org.antlr.v4.runtime.ParserRuleContext ;
import org.antlr.v4.runtime.tree.ParseTreeWalker ;

import xcom.retro.xa.XA.AssemblyContext ;
import xcom.utils4j.logging.aspects.api.annotations.Log ;


public class ExpressionUtils {

	@Log
	public static _ExprNode EXPR_buildArgumentExpressionTree(final AssemblyContext actx, final ParserRuleContext pctx) {

		final ExpressionListener listener = new ExpressionListener(actx) ;
		final ParseTreeWalker walker = new ParseTreeWalker() ;
		walker.walk(listener, pctx) ;

		return listener.expr ;
	}


	public static byte[] EXPR_asBytes(final int value) {
		return ByteBuffer.allocate(4).putInt(value).array() ;
	}


	public static byte[] EXPR_asBytes(final String value) {
		return value.getBytes() ;
	}


	public static Integer EXPR_asInteger(final byte[] value) {
		return ByteBuffer.wrap(value).getInt() ;
	}


	public static String EXPR_asString(final byte[] value) {
		return new String(value) ;
	}


	public static byte EXPR_lsb(final byte[] bytes) {
		return bytes[bytes.length - 1] ;
	}


	public static byte EXPR_msb(final byte[] bytes) {
		return bytes[bytes.length - 2] ;
	}


	public static Object EXPR_formatAsHexLiterial(final Object value) {

		if ( value instanceof String )
			return value ;

		return String.format("0x%0" + ((int) value > 255 ? 4 : 2) + "X", value) ;
	}
}
