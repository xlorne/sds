var init = function () {


    http.get('/admin/models', '加载数据...', function (res) {

        var list = $("#list");
        list.empty();
        for (var p in res) {
            var v = res[p];

            var tr = '<tr>' +
                '<td><a class="model-name" href="#">' + v + '</a></td>' +
                '</tr>';
            list.append(tr);
        }

    });
};


init();


$(document).on("click", ".model-name", function () {
    var key = $(this).text();

    http.get('/admin/connections?key=' + key, '加载数据...', function (res) {

        var list = $("#device");
        list.empty();
        for (var p in res) {
            var data = res[p];
            var tr = '<tr><td><a  href="#">' + data + '</a></td></tr>';
            list.append(tr);
        }

    });

    return false;
});

