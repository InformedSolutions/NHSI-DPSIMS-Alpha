var taxonomy =
    {
        'serviceAreas': [
            {
                "id": "acute",
                "name": "Acute"
            },
            {
                "id": "ambulance-trust",
                "name": "Ambulance Trust"
            },
            {
                "id": "community-nursing",
                "name": "Community Nursing"
            },
            {
                "id": "care-home",
                "name": "Care Home"
            },
            {
                "id": "dental-practice",
                "name": "Dental Practice"
            },
            {
                "id": "community-hospital",
                "name": "Community Hospital"
            },
            {
                "id": "community-pharmacy",
                "name": "Community Pharmacy"
            },
            {
                "id": "general-practice",
                "name": "General Practice"
            },
            {
                "id": "social-care",
                "name": "Social Care"
            },
            {
                "id": "screening-service",
                "name": "Screening Service"
            },
            {
                "id": "not-known",
                "name": "Not Known"
            }
        ],
        'categories' : [
            {
                'id': "A",
                'name': "Access, admission, transfer, discharge (including missing patient)",
                'type': [
                    "incident",
                    "outcome"
                ],
                'subCategories' : [
                    {
                        'id': "A0100",
                        'name': "Absconder / missing patient",
                        'type': "outcome"
                    },
                    {
                        'id': "A0200",
                        'name': "Access / admission - delay / failure in access to hospital / care",
                        'type': "incident"
                    },
                    {
                        'id': "A0800",
                        'name': "Discharge - inappropriate",
                        'type': "incident"
                    }
                ]
            },
            {
                'id': "B",
                'name': "Clinical assessment (including diagnosis, scans, tests, assessments)",
                'type': [
                    "incident",
                    "outcome"
                ],
                'subCategories' : [
                    {
                        'id': "B1200",
                        'name': "Scans / X-rays / specimens - mislabelled / unlabelled",
                        'type': "incident"
                    },
                    {
                        'id': "B1700",
                        'name': "Test results / reports - missing",
                        'type': "outcome"
                    },
                    {
                        'id': "B1800",
                        'name': "Tests - failure / delay to undertake",
                        'type': "incident"
                    }
                ]
            },
            {
                'id': "D",
                'name': "Disruptive, aggressive behaviour (includes patient to patient)",
                'type': [
                    "incident"
                ],
                'subCategories' : [
                    {
                        'id': "D0100",
                        'name': "Physical",
                        'type': "incident"
                    },
                    {
                        'id': "D0200",
                        'name': "Racial",
                        'type': "incident"
                    },
                    {
                        'id': "D0400",
                        'name': "Verbal",
                        'type': "incident"
                    }
                ]
            },
            {
                'id': "F",
                'name': "Infection Control Incident",
                'type': [
                    "incident",
                    "outcome"
                ],
                'subCategories' : [
                    {
                        'id': "F0300",
                        'name': "Failure of sterilisation or contamination of equipment",
                        'type': "incident"
                    },
                    {
                        'id': "F0500",
                        'name': "Infection - wound",
                        'type': "outcome"
                    },
                    {
                        'id': "F1000",
                        'name': "Treatment / procedure - delay / failure",
                        'type': "incident"
                    }
                ]
            },
            {
                'id': "Q",
                'name': "Pressure Ulcer",
                'type': [
                    "outcome"
                ],
                'subCategories' : [
                    {
                        'id': "Q0100",
                        'name': "Acquired during NHS care",
                        'type': "outcome",
                        'linkedCategories': ["F0300"]
                    }
                ]
            }
        ]
    };

module.exports = taxonomy;
