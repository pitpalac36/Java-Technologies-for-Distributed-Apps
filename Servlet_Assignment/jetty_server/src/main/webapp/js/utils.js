function goAdd() {
    $.get("DispatcherController",
        {
            action: "add"
        },
        function (data) {
            window.location.href = "add.jsp";
        }
    );
}
