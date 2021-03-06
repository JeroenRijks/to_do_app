var appRouter = function (app) {
    var Request = require('request');

    app.get("/java/task/all", function(req, res) {
        Request.get("http://localhost:8080/api/task/all", (error, response, body) => {
            if(error) {
                return console.dir(error);
            }
            res.status(200).send(body);
//            console.dir(JSON.parse(body));
        });
    });

    app.get('/java/task/:taskId', function(req, res) {
        Request.get("http://localhost:8080/api/task/" + req.params.taskId, (error, response, body) => {
            if(error) {
                return console.dir(error);
            }
            res.status(200).send(body);
        });
    });

//  Untested post method
    app.post('/java/task/add', function(req, res){
        Request.post("http://localhost:8000/api/task/add/", (error, response, body) => {
//            No need for this commented out stuff, as we want it attached to the body already.
//            How do I show this as Task task, not the individual fields of Task task?
//            var task_id = req.body.task_id;
//            var date = req.body.date;
//            var Importance = req.body.Importance;
//            var task_name = req.body.task_name;
//            var category_id = req.body.category_id;
            if(error) {
                return console.dir(error);
            }
            res.send(body);         // Status code?
        });
    });

//  Untested deletebyid method
    app.post('/java/api/task/delete/:taskId', function(req, res) {
        Request.post("http://localhost:8000/api/task/add/", (error, response, body) => {
//          Value for task_id is in body, so it's being sent through to java.
//            var task_id = req.body.task_id;
            if(error) {
                return console.dir(error);
            }
            res.send(body);         // Status code?
        });
    });

    app.get("/java/category/all", function(req, res) {
        Request.get("http://localhost:8080/api/category/all", (error, response, body) => {
            if(error) {
                return console.dir(error);
            }
            res.status(200).send(body);
        });
    });

    app.get("/java/category/:categoryId", function(req, res) {
        Request.get("http://localhost:8080/api/category/" + req.params.categoryId, (error, response, body) => {
            if(error) {
                return console.dir(error);
            }
            res.status(200).send(body);
        });
    });

    app.put("/java/category/:categoryId", function(req, res) {
        Request.get("http://localhost:8080/api/category/" + req.params.categoryId, (error, response, body) => {
            // category id is in req.body.category_id
            if(error) {
                return console.dir(error);
            }
            res.status(200).send(body);
//            console.dir(JSON.parse(body));
        });
    });

    app.post("/java/category/add", function(req, res) {
        Request.post("http://localhost:8000/api/category/add/", (error, response, body) => {
            // category stats are handed in, but are in separate fields of info atm.
            if(error) {
                console.dir(error);
            }

        });
    });




}






module.exports = appRouter;