
/**
  * encoding.h: Low level encoding of ATerm datatype.
  */

#ifndef ENCODING_H
#define ENCODING_H

#define	MASK_QUOTED	(1<<0)
#define	MASK_ANNO	MASK_QUOTED
#define MASK_MARK	(1<<1)
#define MASK_ARITY	((1<<2) | (1<<3) | (1<<4))
#define MASK_TYPE	((1<<5) | (1<<6) | (1<<7))

#define SHIFT_ARITY   2
#define SHIFT_TYPE    5
#define SHIFT_LENGTH  8
#define SHIFT_SYMBOL  SHIFT_LENGTH
#define SHIFT_SYM_ARITY SHIFT_LENGTH

#define TERM_SIZE_INT         3
#define TERM_SIZE_REAL        4
#define TERM_SIZE_BLOB        4
#define TERM_SIZE_LIST        4
#define TERM_SIZE_PLACEHOLDER 3
#define TERM_SIZE_SYMBOL      (sizeof(struct SymEntry)/sizeof(header_type))

#define IS_MARKED(h)    ((h) & MASK_MARK)
#define GET_TYPE(h)     (((h) & MASK_TYPE) >> SHIFT_TYPE)
#define HAS_ANNO(h)     ((h) & MASK_ANNO)
#define GET_ARITY(h)	(((h) & MASK_ARITY) >> SHIFT_ARITY)
#define GET_SYMBOL(h)	((h) >> SHIFT_SYMBOL)
#define GET_LENGTH(h)	((h) >> SHIFT_LENGTH)
#define IS_QUOTED(h)	((h) & MASK_QUOTED)

#define SET_MARK(h)			((h) |= MASK_MARK)
#define SET_ANNO(h)			((h) |= MASK_ANNO)
#define SET_ARITY(h, ar)    ((h) = (((h) & ~MASK_ARITY) | \
									((ar) << SHIFT_ARITY)))
#define SET_SYMBOL(h, sym)	((h) = (((h) & ~MASK_SYMBOL) | \
									((sym) << SHIFT_SYMBOL)))
#define SET_LENGTH(h, len)  ((h) = (((h) & ~MASK_LENGTH) | \
									((len) << SHIFT_LENGTH)))
#define SET_QUOTED(h)		((h) |= MASK_QUOTED)

#define CLR_MARK(h)			((h) &= ~MASK_MARK)
#define CLR_ANNO(h)			((h) &= ~MASK_ANNO)
#define CLR_QUOTED(h)		((h) &= ~MASK_QUOTED)

#define APPL_HEADER(anno,ari,sym) ((anno) | ((ari) << SHIFT_ARITY) | \
				   (AT_APPL << SHIFT_TYPE) | \
				   ((header_type)(sym) << SHIFT_SYMBOL))
#define INT_HEADER(anno)          ((anno) | AT_INT << SHIFT_TYPE)
#define REAL_HEADER(anno)         ((anno) | AT_REAL << SHIFT_TYPE)
#define EMPTY_HEADER(anno)        ((anno) | AT_LIST << SHIFT_TYPE)
#define LIST_HEADER(anno,len)     ((anno) | (AT_LIST << SHIFT_TYPE) | \
				   (len << SHIFT_LENGTH) | (2 << SHIFT_ARITY))
#define PLACEHOLDER_HEADER(anno)  ((anno) | (AT_PLACEHOLDER << SHIFT_TYPE) | \
           1 << SHIFT_ARITY)
#define BLOB_HEADER(anno,len)     ((anno) | (AT_BLOB << SHIFT_TYPE) | \
				   ((len) << SHIFT_LENGTH))
#define SYMBOL_HEADER(arity,quoted) (((quoted) ? MASK_QUOTED : 0) | \
		       ((arity) << SHIFT_SYM_ARITY) | (AT_SYMBOL << SHIFT_TYPE))
#define FREE_HEADER               (AT_FREE << SHIFT_TYPE)

#define ARG_OFFSET 2

/* This assumes 32 bits int */
typedef unsigned int header_type;
#define HEADER_BITS 32

#endif
