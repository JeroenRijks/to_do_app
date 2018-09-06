var appRouter = function (app) {
    app.get("/", function(req, res) {


    var Request = require("request");

    Request.get("http://localhost:8000/api/resource/", (error, response, body) => {
        if(error) {
            return console.dir(error);
        }
        res.status(200).send(body);
        console.dir(JSON.parse(body));
    });

//        res.status(200).send("Welcome to our restful API");
    });
}






module.exports = appRouter;