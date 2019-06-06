<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <spring:theme code="stylesheet" var="themeName" />
    <link href='<spring:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>' rel="stylesheet" />
    <%-- <link href='<spring:url value="/resources/css/${themeName}"/>' rel="stylesheet" />--%>
    <c:url value="/resources/css/main.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />
    
    <c:url value="/webjars/jquery/3.1.1/jquery.min.js" var="jquery" />
	<script type="text/javascript" src="${jquery}"></script>
	<c:url value="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" var="bootstrapJS" />
	<script type="text/javascript" src="${bootstrapJS}"></script>
    
    