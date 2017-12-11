var express = require('express')
var router = express.Router()

// Route index page
router.get('/', function (req, res) {
  res.render('index')
});

router.post('/mds1', function (req, res) {
    res.render('record-type/index');
});

router.get('/mds1', function (req, res) {
    res.render('record-type/index');
});

router.post('/mds2', function (req, res) {
    var recordType = req.body["record-type"];
    req.session.recordType = recordType;

    if (recordType == 'Risk') {
        res.render('risk/index');
    }
    else if (recordType == "PSI") {
        res.render('level-of-harm/incident');
    } else {
        res.render('level-of-harm/outcome');
    }
});

router.get('/mds2', function (req, res) {
    var recordType = req.session.recordType;

    if (recordType == 'Risk') {
        res.render('risk/index');
    }
    else if (recordType == "PSI") {
        res.render('level-of-harm/incident');
    } else {
        res.render('level-of-harm/outcome');
    }
});

router.post('/mds3', function (req, res) {
    res.render('details/index');
});

router.get('/mds3', function (req, res) {
    var recordType = req.session.recordType;

    if (recordType == 'Risk') {
        res.render('risk/index');
    }
     else {
        res.render('details/index');
    }
});

router.post('/check-your-answers', function (req, res) {
    res.render('check-your-answers/index');
});

module.exports = router;
