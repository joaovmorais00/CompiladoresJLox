package edu.ufsj.lox;

import java.util.List;

abstract class Expr{
	interface Visitor <R > {
	R visitBinaryExpr(Binary expr);
	R visitTernaryExpr(Ternary expr);
	R visitGroupingExpr(Grouping expr);
	R visitLiteralExpr(Literal expr);
	R visitUnaryExpr(Unary expr);
}
static class Binary extends Expr {
	Binary(Expr left,Token operator,Expr right) {
	this.left = left;
	this.operator = operator;
	this.right = right;
}

	@Override
	<R> R accept(Visitor <R> visitor) {
	return visitor.visitBinaryExpr(this);
	}

	final Expr left;
	final Token operator;
	final Expr right;
}

static class Ternary extends Expr {
	Ternary(Expr expr, Expr term1,Expr term2) {
	this.expr = expr;
	this.term1 = term1;
	this.term2 = term2;
}

	@Override
	<R> R accept(Visitor <R> visitor) {
	return visitor.visitTernaryExpr(this);
	}

	final Expr expr;
	final Expr term1;
	final Expr term2;
}


static class Grouping extends Expr {
	Grouping(Expr expression) {
	this.expression = expression;
}

	@Override
	<R> R accept(Visitor <R> visitor) {
	return visitor.visitGroupingExpr(this);
	}

	final Expr expression;
}




static class Literal extends Expr {
	Literal(Object value) {
	this.value = value;
}

	@Override
	<R> R accept(Visitor <R> visitor) {
	return visitor.visitLiteralExpr(this);
	}

	final Object value;
}
static class Unary extends Expr {
	Unary(Token operator,Expr right) {
	this.operator = operator;
	this.right = right;
}

	@Override
	<R> R accept(Visitor <R> visitor) {
	return visitor.visitUnaryExpr(this);
	}

	final Token operator;
	final Expr right;
}

	abstract <R> R accept( Visitor <R> visitor);
}
