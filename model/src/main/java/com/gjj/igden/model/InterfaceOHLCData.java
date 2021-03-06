package com.gjj.igden.model;

import org.javatuples.Ennead;

/**
 * http://www.javatuples.org/
 * the data structure names :
 Unit<A> (1 element)
 Pair<A,B> (2 elements)
 Triplet<A,B,C> (3 elements)
 Quartet<A,B,C,D> (4 elements)
 Quintet<A,B,C,D,E> (5 elements)
 Sextet<A,B,C,D,E,F> (6 elements)
 Septet<A,B,C,D,E,F,G> (7 elements)
 Octet<A,B,C,D,E,F,G,H> (8 elements)
 Ennead<A,B,C,D,E,F,G,H,I> (9 elements)
 Decade<A,B,C,D,E,F,G,H,I,J> (10 elements)
 *
 */
public interface InterfaceOHLCData extends IMarketData {

	Ennead<InstId,Long,Long,Integer,Double,Double,Double,Double,Long> getMainData();
}
