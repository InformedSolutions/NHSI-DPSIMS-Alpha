var express = require('express')
var router = express.Router()
var taxonomy = require ('./taxonomy.js');
const journeyA = "Journey A - Select record type first";
const journeyB = "Journey B - Select category first";

// Route index page
router.get('/', function (req, res) {
    req.session.destroy();
    res.render('index')
});

router.post('/selected-journey', function (req, res) {
    if (req.body["button"] === journeyA){
        req.session.journey = "A";
        res.redirect('/record-type');
    } else if (req.body["button"] === journeyB){
        req.session.journey = "B";
        res.redirect('/category');
    }
    //res.redirect('incident-recorder/index');
});

/*router.post('/selected-recorder', function (req, res) {
    if (req.session.journey === "A") {
        res.redirect('/record-type');
    }
    else if (req.session.journey === "B") {
        res.redirect('/category');
    }
});*/

router.post('/selected-type', function (req, res) {
    req.session.recordType = req.body["record-type"];

    if (req.session.recordType == 'risk') {
        res.redirect('/risk-details');
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

router.post('/category', function (req, res) {
    res.render('category/index', {
        "categories": taxonomy.categories,
        "recordType": req.session.recordType,
        "journey" : req.session.journey
    })
});

router.get('/category', function (req, res) {
    res.render('category/index', {
        "categories": taxonomy.categories,
        "recordType": req.session.recordType,
        "journey" : req.session.journey
    })
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

router.post('/selected-level-of-harm', function (req, res) {
    if (req.session.journey === "A") {
        res.redirect('/category');
    }
    else if (req.session.journey === "B") {
        res.redirect('/incident-description')
    }
});



module.exports = router;
