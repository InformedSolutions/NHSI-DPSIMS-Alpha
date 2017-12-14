var express = require('express')
var router = express.Router()

// Route index page
router.get('/', function (req, res) {
  res.render('index')
});

router.post('/selected-type', function (req, res) {
    var recordType = req.body["record-type"];
    req.session.recordType = recordType;

    if (recordType == 'Risk') {
        res.redirect('/risk-details');
    }
    else if (recordType == "PSI") {
        res.redirect('/level-of-harm/incident');
    } else {
        res.redirect('/level-of-harm/outcome');
    }
});

router.get('/level-of-harm', function (req, res) {
    var recordType = req.session.recordType;

    if (recordType == "PSI") {
        res.redirect('/level-of-harm/incident');
    } else {
        res.redirect('/level-of-harm/outcome');
    }
});

module.exports = router;
