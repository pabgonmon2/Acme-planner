<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="administrator.spamfilter.list.label.spamwords" path="spamwords" width="80%"/>
	<acme:list-column code="administrator.spamfilter.list.label.threshold" path="threshold" width="20%"/>
</acme:list>