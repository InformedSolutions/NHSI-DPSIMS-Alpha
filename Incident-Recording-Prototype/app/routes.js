var express = require('express')
var router = express.Router()
var taxonomy = require ('./taxonomy.js');

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

router.post('/incident-subcategory', function (req, res) {
    var incidentCategory = req.body["incident-type"];
    req.session.incidentCategory = incidentCategory;

    if (incidentCategory == 'Access, admission, transfer, discharge (including missing patient)') {
        res.redirect('/incident-subcategory/access');
    }
    else if (incidentCategory == "Clinical assessment (including diagnosis, scans, tests, assessments)") {
        res.redirect('/incident-subcategory/clinical-assessment');
    }
    else if (incidentCategory == "Consent, communication, confidentiality)") {
        res.redirect('/incident-subcategory/consent');
    }
    else if (incidentCategory == "Disruptive, aggressive behaviour (includes patient to patient))") {
        res.redirect('/incident-subcategory/disruptive');
    }
    else if (incidentCategory == "Documentation (including electronic & paper records, identification and charts)") {
        res.redirect('/incident-subcategory/documentation');
    }
    else if (incidentCategory == "Implementation of care and ongoing monitoring / review") {
        res.redirect('/incident-subcategory/implementation');
    }
    else if (incidentCategory == "Infection Control Incident") {
        res.redirect('/incident-subcategory/infection');
    }
    else if (incidentCategory == "Infrastructure (including staffing, facilities, environment)") {
        res.redirect('/incident-subcategory/infrastructure');
    }
    else if (incidentCategory == "Medical device / equipment") {
        res.redirect('/incident-subcategory/device');
    }
    else if (incidentCategory == "Medication") {
        res.redirect('/incident-subcategory/medication');
    }
    else if (incidentCategory == "Patient abuse (by staff / third party)") {
        res.redirect('/incident-subcategory/abuse');
    }
    else if (incidentCategory == "Patient accident") {
        res.redirect('/incident-subcategory/accident');
    }
    else if (incidentCategory == "Self-harming behaviour") {
        res.redirect('/incident-subcategory/self-harming');
    }
    else if (incidentCategory == "Treatment, procedure") {
        res.redirect('/incident-subcategory/treatment');
    }
    else if (incidentCategory == "Maternal fetal neonatal incidents CNST triggers") {
        res.redirect('/incident-subcategory/maternal');
    }
    else if (incidentCategory == "Pressure ulcer") {
        res.redirect('/incident-subcategory/ulcer');
    }
    else if (incidentCategory == "Other") {
        res.redirect('/incident-subcategory/other');
    }
});

router.get('/incident-subcategory', function (req, res) {
    var incidentCategory = req.session.incidentCategory;

    if (incidentCategory == 'Access, admission, transfer, discharge (including missing patient)') {
        res.redirect('/incident-subcategory/access');
    }
    else if (incidentCategory == "Clinical assessment (including diagnosis, scans, tests, assessments)") {
        res.redirect('/incident-subcategory/clinical-assessment');
    }
    else if (incidentCategory == "Consent, communication, confidentiality)") {
        res.redirect('/incident-subcategory/consent');
    }
    else if (incidentCategory == "Disruptive, aggressive behaviour (includes patient to patient))") {
        res.redirect('/incident-subcategory/disruptive');
    }
    else if (incidentCategory == "Documentation (including electronic & paper records, identification and charts)") {
        res.redirect('/incident-subcategory/documentation');
    }
    else if (incidentCategory == "Implementation of care and ongoing monitoring / review") {
        res.redirect('/incident-subcategory/implementation');
    }
    else if (incidentCategory == "Infection Control Incident") {
        res.redirect('/incident-subcategory/infection');
    }
    else if (incidentCategory == "Infrastructure (including staffing, facilities, environment)") {
        res.redirect('/incident-subcategory/infrastructure');
    }
    else if (incidentCategory == "Medical device / equipment") {
        res.redirect('/incident-subcategory/device');
    }
    else if (incidentCategory == "Medication") {
        res.redirect('/incident-subcategory/medication');
    }
    else if (incidentCategory == "Patient abuse (by staff / third party)") {
        res.redirect('/incident-subcategory/abuse');
    }
    else if (incidentCategory == "Patient accident") {
        res.redirect('/incident-subcategory/accident');
    }
    else if (incidentCategory == "Self-harming behaviour") {
        res.redirect('/incident-subcategory/self-harming');
    }
    else if (incidentCategory == "Treatment, procedure") {
        res.redirect('/incident-subcategory/treatment');
    }
    else if (incidentCategory == "Maternal fetal neonatal incidents CNST triggers") {
        res.redirect('/incident-subcategory/maternal');
    }
    else if (incidentCategory == "Pressure ulcer") {
        res.redirect('/incident-subcategory/ulcer');
    }
    else if (incidentCategory == "Other") {
        res.redirect('/incident-subcategory/other');
    }
});


router.post('/service-area', function (req, res) {
    res.render('service-area/index', {
        "serviceAreas": taxonomy.serviceAreas
    })
});


module.exports = router;
