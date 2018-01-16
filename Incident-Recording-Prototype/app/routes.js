var express = require('express')
var router = express.Router()
var taxonomy = require ('./taxonomy.js');
const journeyA = "Journey A - Healthcare Professional";
const journeyB = "Journey B - Member of the Public";

// Route index page
router.get('/', function (req, res) {
    req.session.destroy();
    res.render('index')
});

router.post('/selected-journey', function (req, res) {
    if (req.body["button"] === journeyA){
        req.session.journey = "A";
    } else if (req.body["button"] === journeyB){
        req.session.journey = "B";
    }
    res.redirect('record-type/index');
});

router.post('/selected-type', function (req, res) {
    req.session.recordType = req.body["record-type"];

    if (req.session.recordType == 'risk') {
        res.redirect('/risk/description');
    } else {
        res.redirect('/description');
    }
});

router.get('/description', function (req, res){
   res.render('description/index', {
       "recordType": req.session.recordType
   });
});

router.get('/level-of-harm', function (req, res) {
    var recordType = req.session.recordType;

    if (recordType == "incident") {
        res.redirect('/level-of-harm/incident');
    } else {
        res.redirect('/level-of-harm/outcome');
    }
});

router.post('/level-of-harm', function (req, res) {
    var recordType = req.session.recordType;

    if (recordType == "incident") {
        res.redirect('/level-of-harm/incident');
    } else {
        res.redirect('/level-of-harm/outcome');
    }
});

router.post('/date', function (req, res){
    res.render('date/index', {
        "recordType": req.session.recordType
    });
});

router.get('/date', function (req, res){
    res.render('date/index', {
        "recordType": req.session.recordType
    });
});

router.post('/service-area', function (req, res) {
    res.render('service-area/index', {
        "serviceAreas": taxonomy.serviceAreas
    })
});

router.get('/service-area', function (req, res) {
    res.render('service-area/index', {
        "serviceAreas": taxonomy.serviceAreas
    })
});

router.post('/theme', function (req, res) {
    res.render('theme/index', {
        "themes": taxonomy.themes,
        "recordType": req.session.recordType,
        "journey" : req.session.journey
    })
});

router.get('/theme', function (req, res) {
    res.render('theme/index', {
        "themes": taxonomy.themes,
        "recordType": req.session.recordType,
        "journey" : req.session.journey
    })
});

router.get('/never-event', function (req, res) {
    res.render('never-event/index', {
        "recordType": req.session.recordType
    });
});


router.post('/selected-never-event', function (req, res) {

    var isNeverEvent = req.body['never-event'];
    var neverEventType = req.body['never-event-type-select'];

    if(isNeverEvent) {
        res.redirect('/level-of-harm');
    } else {
        res.render('never-event/index', {
            recordType: req.session.recordType,
            err: 'Please indicate if this was a Never Event'
        })
    }
});

router.post('/subcategory', function (req, res) {
    var selectedCategory = req.body["category"];
    var subCategories = [];
    taxonomy.categories.forEach(
        function(category){
            if (category.name === selectedCategory){
                req.session.selectedCategory = category;
                subCategories = category.subCategories;
            }
        }
    );
    res.render('subcategory/index', {
        "subCategories": subCategories,
        "recordType": req.session.recordType,
        "journey" : req.session.journey
    })
});

router.get('/patient/gender', function (req, res) {
    res.render('patient/gender', {
        "recordType": req.session.recordType
    });
})

router.get('/subcategory', function (req, res) {
    var selectedCategory = req.session.selectedCategory;
    var subCategories = selectedCategory.subCategories;
    res.render('subcategory/index', {
        "subCategories": subCategories,
        "recordType": req.session.recordType,
        "journey" : req.session.journey
    })
});

router.post('/selected-subcategory', function (req, res) {
    var selectedSubCategory = req.body["subcategory"];
    req.session.selectedCategory.subCategories.forEach(
        function(subCategory){
            if (subCategory.name === selectedSubCategory){
                req.session.selectedSubCategory = subCategory;
            }
        }
    );
    if (req.session.recordType === 'incident' && req.session.selectedSubCategory.type === 'outcome'){
        res.redirect('/suggested-categories')
    } else {
        res.redirect('/level-of-harm');
    }
});

router.get('/suggested-categories', function (req, res) {
    var linkedCategories = req.session.selectedSubCategory.linkedCategories;
    var linkedCategoryDetails = [];
    linkedCategories.forEach(function(linkedCategoryId){
        taxonomy.categories.forEach(function (category){
            category.subCategories.forEach(function(subCategory){
                if(subCategory.id === linkedCategoryId){
                    linkedCategoryDetails.push(subCategory);
                }
            });
        });
    });
    res.render('suggested-categories/index', {
        'linkedIncidentCategories' : linkedCategoryDetails
    })
});

router.get('/get-sub-service-area/:query', function(req, res) {
   var serviceArea = req.params.query;
   res.json(taxonomy.subServiceAreas[serviceArea]);
});

router.get('/check-your-answers', function (req, res) {
   res.render('check-your-answers/index', {
       recordType: (req.session.recordType || "Incident").toLowerCase().replace(/\b(\w)/g,function(t) {return t.toUpperCase()})
   });
});

router.post('/risk/theme', function (req, res) {
    res.render('risk/theme', {
        "themes": taxonomy.riskThemes,
    })
});

router.get('/risk/theme', function (req, res) {
    res.render('risk/theme', {
        "themes": taxonomy.riskThemes,
    })
});

router.post('/risk/location', function (req, res) {
    res.render('risk/location', {
        "serviceAreas": taxonomy.serviceAreas
    })
});

router.get('/risk/location', function (req, res) {
    res.render('risk/location', {
        "serviceAreas": taxonomy.serviceAreas
    })
});

router.post('/confirmation', function (req, res) {
    res.render('confirmation/index', {
        recordType:  req.session.recordType
    });
});

router.get('/confirmation', function (req, res) {
    res.render('confirmation/index', {
        recordType: req.session.recordType
    });
});

module.exports = router;
