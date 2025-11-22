

package xcom.retro.xa.api.annotations ;


import static java.lang.annotation.ElementType.TYPE ;
import static java.lang.annotation.RetentionPolicy.RUNTIME ;

import java.lang.annotation.Retention ;
import java.lang.annotation.Target ;

import org.antlr.v4.runtime.Lexer ;
import org.antlr.v4.runtime.Parser ;
import org.antlr.v4.runtime.tree.ParseTreeListener ;


@Retention(RUNTIME)
@Target(TYPE)
public @interface aProcessor {

	Class<? extends Lexer> lexar();

	Class<? extends Parser> parser();

	Class<? extends ParseTreeListener> listener();
	
	String[] extensions() ;
}
