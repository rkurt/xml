<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
        integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
        crossorigin="anonymous"></script>

<script type="text/javascript">
    function setFileName(fileName) {
        $('.custom-file-control').html(fileName);
    }

    function hideMessage() {
        $('#message').hide();
    }

    (function() {
        var $file = $('#file');
        if ($file[0] && $file[0].files.length > 0) {
            setFileName($file[0].files[0].name);
            hideMessage();
        }

        $file.change(function(e){
            setFileName(e.target.files[0].name);
            hideMessage()
        });
    })();
</script>