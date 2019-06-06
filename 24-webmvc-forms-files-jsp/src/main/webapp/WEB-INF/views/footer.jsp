<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
	
   	<spring:url value="" var="currentPage"/>
	<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('${currentPage}?lang=' + selectedOption);
            }
        });
    });
    </script>
