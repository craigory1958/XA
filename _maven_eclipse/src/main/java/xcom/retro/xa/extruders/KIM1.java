

package xcom.retro.xa.extruders ;


import java.io.PrintWriter ;
import java.util.Arrays ;
import java.util.function.BiConsumer ;
import java.util.function.Consumer ;

import xcom.retro.xa.ByteBlock ;
import xcom.retro.xa.Segment ;
import xcom.retro.xa.XA.AssemblyContext ;
import xcom.retro.xa.api.annotations.aExtruder ;
import xcom.retro.xa.api.interfaces.iExtruder ;


@aExtruder(format = "KIM1")
public class KIM1 implements iExtruder {

	private static final int BlockSize = 24 ;


	public KIM1() {}


	@Override
	public void extrude(final PrintWriter out, final AssemblyContext actx) {

		actx.segments().forEach(new BiConsumer<String, Segment>() {
			@Override
			public void accept(final String k, final Segment segment) {
				segment.blocks().forEach(new Consumer<ByteBlock>() {
					@Override
					public void accept(final ByteBlock block) {
						for ( int i = 0; i < block.numBytes(); i += BlockSize )
							extrudeRecord(out, block.org() + i,
									Arrays.copyOfRange(block.bytes(), i, (i < (block.numBytes() - BlockSize) ? i + BlockSize : block.numBytes()))) ;
					}
				}) ;
			}
		}) ;
	}


	void extrudeRecord(final PrintWriter out, final int address, final byte[] bytes) {

		out.print(String.format(";%02X%04X", bytes.length, address)) ;

		int checksum = 0 ;
		for ( final byte b : bytes ) {
			out.print(String.format("%02X", b)) ;
			checksum += b ;
		}

		out.println(String.format("%04X", checksum & 0xFFFF)) ;
	}
}
