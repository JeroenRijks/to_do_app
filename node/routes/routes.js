var appRouter = function (app) {
    var Request = require('request');

    app.get("/java/task/all", function(req, res) {
        Request.get("http://localhost:8080/api/task/all", (error, response, body) => {
            if(error) {return console.dir(error);}
            res.status(200).send(body);
        });
    });

    app.get('/java/task/:taskId', function(req, res) {
        Request.get("http://localhost:8080/api/task/" + req.params.taskId, (error, response, body) => {
            if(error) {return console.dir(error);}
            res.status(200).send(body);
        });
    });

    app.post('/java/task/add', function(req, res){
        Request({
            method: 'POST',
            url: 'http://localhost:8080/api/task/add',
            body: req.body,
            json: true,
            headers: {
                'Content-Type': 'application/json'
            }
        }, (error, response, body) => {
            if(error) {return console.dir(error);}
            res.send(body);         // Status code?
        });
    });

    app.put('/java/task/:taskId', function(req, res){
        console.dir("The variable passed in: " + req.params.taskId + ".");

        Request({
              method: 'PUT',
              url: 'http://localhost:8080/api/task/' + req.params.taskId,
              body: req.body,
              json: true,
              headers: {
                'Content-Type': 'application/json'
              }
            }, (error, response, body) => {
//            if(error) {console.dir(error);}
            console.dir("IN THE REQUEST.PUT METHOD");
            res.send(body);
        });

//        Request({
//            method: 'PUT'.
//            url: 'http://localhost:8080/api/task/' + req.params.taskId,
//            body: req.body,
//            json: true,
//            headers: {
//                'Content-Type': 'application/json'
//            }
//        }, (error, response, body) => {
//            if(error) {return console.dir(error);}
//            res.send(body);
//        });
    });

    app.delete('/java/task/:taskId', function(req, res) {
        Request.delete("http://localhost:8080/api/task/" + req.params.taskId, (error, response, body) => {
            if(error) {return console.dir(error);}
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
            if(error) {return console.dir(error);}
            res.status(200).send(body);
        });
    });
    app.put("/java/category/:categoryId", function(req, res) {
        // Attach application/json header to the request body
        Request({
              method: 'PUT',
              url: 'http://localhost:8080/api/category/' + req.params.categoryId,
              body: req.body,
              json: true,
              headers: {
                'Content-Type': 'application/json'
              }
            }, (error, response, body) => {
//            if(error) {console.dir(error);}
            res.send(body);
        });
    });

    app.post("/java/category/add", function(req, res) {
        Request({
            method: 'POST',
            url: 'http://localhost:8080/api/category/add',
            body: req.body,
            json: true,
            headers: {
              'Content-Type': 'application/json'
            }
        }, (error, response, body) => {
//            if(error) {console.dir(error);}
            res.send(body);
        });
    });

    app.delete("/java/category/:categoryId", function(req, res) {
        Request.delete("http://localhost:8080/api/category/" + req.params.categoryId, (error, response, body) => {
            if(error) {return console.dir(error);}
            res.send(body);
        });
    });











}

module.exports = appRouter;