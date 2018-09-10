var appRouter = function (app) {

    app.get("/", function(req, res) {

    var Request = require("request");
    Request.get("http://localhost:8080/api/task/1", (error, response, body) => {
        if(error) {
            return console.dir(error);
        }
        res.status(200).send(body);
        console.dir(JSON.parse(body));
    });

    });

}






module.exports = appRouter;