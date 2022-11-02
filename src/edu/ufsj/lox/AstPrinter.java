package edu.ufsj.lox ;

class AstPrinter implements Expr.Visitor < String > {


	String print ( Expr expr ) {

		return expr.accept ( this );
	}
	
	@Override
	public String visitBinaryExpr ( Expr.Binary expr ) {
		return parenthesize ( expr.operator.lexeme, expr.left , expr.right );
	}
	@Override
	public String visitTernaryExpr ( Expr.Ternary expr ) {
		return parenthesizeTernary ( expr.expr, expr.term1 , expr.term2 );
	}
	@Override
	public String visitGroupingExpr ( Expr.Grouping expr ){
		return parenthesize ( "group" , expr.expression );
	}
	@Override
	public String visitLiteralExpr( Expr.Literal expr ) {
		if ( expr.value == null ) return "nil" ;
		return expr.value.toString ();
	}
	@Override
	public String visitUnaryExpr ( Expr.Unary expr ) {
		return parenthesize ( expr.operator.lexeme, expr.right );
	}
	
	private String parenthesize ( String name , Expr ... exprs ) {
		StringBuilder builder = new StringBuilder();
		builder.append( "(" ).append( name );
		for ( Expr expr : exprs ) {
			builder.append( " " );
			builder.append( expr.accept( this ));
		}
		builder.append( " ) " );
		return builder.toString();
	}
	
	private String parenthesizeTernary (Expr expr, Expr term1, Expr term2 ) {
		StringBuilder builder = new StringBuilder();
		builder.append( "( " ).append(term2.accept(this) );
		builder.append( "(: " ).append(term1 .accept(this));
		builder.append( "( ? " ).append(expr.accept(this)).append(" )");
		builder.append( " ) )" );
		return builder.toString();
	}
	
//	public static void main ( String [] args ) {
//		Expr expression = new Expr . Binary (
//		new Expr . Unary (
//		new Token ( TokenType . MINUS , " -" , null , 1) ,
//		new Expr . Literal (123)) ,
//		new Token ( TokenType . STAR , " * " , null , 1) ,
//		new Expr . Grouping (
//		new Expr . Literal (45.67)));
//		System . out . println (
//		new AstPrinter (). print ( expression ));
//		}
}