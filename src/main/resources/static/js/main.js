var currentRoom = 0;
$(document).ready(function () {
    $("#loginBtn").click(function () {
        login();
    });
    $("#quit").click(function () {
        $.post("/quit", {}, function (resp) {
            location.reload();
        });
    });
    $("#newUser").click(function () {
        $("#login-form").hide();
        $("#registration-form").show();
    });
    $("#bckLogin").click(function () {
        $("#login-form").show();
        $("#registration-form").hide();
    });
    $("#sndMSG").click(function () {
        sendMessage();
    });
    $("#search").click(function () {
        searchRoom();
    });
    $("#mainroom").click(function () {
        changeRoom({id:0});
    });
    $("#newroom").click(function () {
        newRoom();
    });
    $("#register").click(function () {
        var user = {};
        user.login = $("#login").val();
        user.email = $("#email").val();
        user.password = $("#password").val();
        user.passrply = $("#pwd-rpt").val();
        if (validateUser(user)) {
            $.post("/registration", user, function (resp) {
                errorFromResponse(resp);
            })
        } else {
            $("#errorModal").modal("show");
        }
    });

    function validateUser(user) {
        if (user.login.trim().length < 4) {
            $("#errorText").find("p").html("Login is incorrect!");
            return false;
        } else if (!(validateEmail(user.email))) {
            $("#errorText").find("p").html("Email format is incorrect!");
            return false;
        } else if (user.password.trim().length < 4 && user.passrply != user.password) {
            $("#errorText").find("p").html("Passwords not equals!");
            return false;
        }
        return true;
    }

    function validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function errorFromResponse(resp) {
        if (resp.state != undefined) {
            location.reload()
        }
        if (resp.error != undefined) {
            $("#errorText").find("p").html(resp.error);
            $("#errorModal").modal("show");
        }
    }
    function login() {
        var user = {};
        user.login = $("#usr").val();
        user.password = $("#pwd").val();
        $.post("/login", user, function (resp) {
            errorFromResponse(resp);
        })
    }
    $("#msg").on('keydown', function (e) {
        if (e.which == 13) {
            sendMessage();
        }
    });
    $("#searchtxt").on('keydown', function (e) {
        if (e.which == 13) {
            searchRoom();
        }
    });
    $("#pwd").on('keydown', function (e) {
        if (e.which == 13) {
            login();
        }
    });
});

function changeRoom(room) {
    currentRoom = room.id;
    $("#chat-room").empty();
    $("#chathead>.panel-heading").text(currentRoom == 0 ? 'Main room' : 'Chat room is:' + currentRoom)
}
function newRoom() {
    $.post("/secured/create/room", {}, function (resp) {
        if (resp != "") {
            changeRoom(resp);
        } else {
            $("#errorText").find("p").html("Room create error!");
            $("#errorModal").modal("show");
        }
    });
}
function searchRoom() {
    var room = $("#searchtxt").val();
    $.post("/secured/search/room", {room: room}, function (resp) {
        if (resp != "") {
            changeRoom(resp);
        } else {
            $("#errorText").find("p").html("Room not found!");
            $("#errorModal").modal("show");
        }
    });
}

function initMessageManager() {
    getMessages();
    changeRoom({id: 0});
    setInterval(function () {
        getMessages();
    }, 5 * 1000);
}
function getMessages() {
    $.post("/secured/messages", {room: currentRoom}, function (resp) {
        updateMessages(resp);
    });
}
function sendMessage() {
    var message = $("#msg").val();
    if (message.trim().length > 0) {
        jQuery.post("/secured/send/message", {message: message, roomid: currentRoom}, function () {
            getMessages();
            $("#msg").val('');
        })
    } else {
        $("#errorText").find("p").html("Please enter some message!");
        $("#errorModal").modal("show");
    }
}
function updateMessages(resp) {
    $("#chat-room").empty();
    $("#chat-room").html(resp);
    var objDiv = document.getElementById("chat-room");
    objDiv.scrollTop = objDiv.scrollHeight;
}


