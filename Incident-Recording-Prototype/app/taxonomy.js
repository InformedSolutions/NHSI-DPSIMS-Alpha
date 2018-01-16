var taxonomy =
    {
        'serviceAreas': [
            {
                "id": "acute",
                "name": "Acute Hospital"
            },
            {
                "id": "ambulance",
                "name": "Ambulance"
            },
            {
                "id": "community-hospital",
                "name": "Community Hospital"
            },
            {
                "id": "community-nursing",
                "name": "Community Nursing"
            },
            {
                "id": "community-pharmacy",
                "name": "Community Pharmacy"
            },
            {
                "id": "community-therapy",
                "name": "Community Therapy"
            },
            {
                "id": "dental-practice",
                "name": "Dental Practice"
            },
            {
                "id": "general-practice",
                "name": "General Practice"
            },
            {
                'id': "maternal",
                'name': "Maternity services (obstetric and midwifery services, including community midwifery)"
            },
            {
                "id": "residential-nursing",
                "name": "Residential Nursing"
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
        'subServiceAreas' : {
            'Not Known': [],
            'Acute Hospital': [
                'Emergency department',
                'Theatres, including recovery',
                'Outpatient department',
                'Adult ITU or HDU',
                'Paediatric ITU or HDU',
                'Neonatal intensive care unit/special care baby unit',
                'Maternity services (except NICU/SCBU)',
                'Adult medical inpatient services (general or specialist)',
                'Adult surgical inpatient services (general or specialist)',
                'Paediatric inpatient services (general or specialist)',
                'Adult medical treatment or assessment unit (not overnight stay)',
                'Adult surgical treatment or assessment unit (not overnight stay)',
                'Paediatric treatment or assessment unit (not overnight stay)',
                'Diagnostic services',
                'Therapy services',
                'Pharmacy services',
                'Other'
            ],
            'Ambulance': [],
            'Community Nursing': [],
            'Residential Nursing': [],
            'Dental Practice': [],
            'Community Hospital': [],
            'Community Pharmacy': [],
            'Community Therapy': [],
            'General Practice': [],
            'Social Care': [],
            'Screening Service': []
        },
        'themes': [
            {
                'id': "access",
                'name': "Access, admission, transfer, discharge"
            },
            {
                'id': "clinical-assessment",
                'name': "Clinical assessment"
            },
            {
                'id': "consent",
                'name': "Consent, communication, confidentiality"
            },
            {
                'id': "disruptive",
                'name': "Disruptive aggressive behaviour"
            },
            {
                'id': "documentation",
                'name': "Documentation"
            },
            {
                'id': "infection",
                'name': "Infection control"
            },
            {
                'id': "implementation",
                'name': "Implementation of care and ongoing monitoring/review"
            },
            {
                'id': "infrastructure",
                'name': "Infrastructure"
            },
            {
                'id': "it",
                'name': "IT system failure"
            },
            {
                'id': "device",
                'name': "Medical device or equipment"
            },
            {
                'id': "medication",
                'name': "Medication"
            },
            {
                'id': "abuse",
                'name': "Patient abuse"
            },
            {
                'id': "accident",
                'name': "Patient accident"
            },
            {
                'id': "self-harm",
                'name': "Self-harming behaviour"
            },
            {
                'id': "treatment",
                'name': "Treatment procedures"
            },
            {
                'id': "other",
                'name': "Other"
            }
        ],
        'riskThemes' : [
            {
                'id': "staffing",
                'name': "Staffing, workload and capacity of services"
            },
            {
                'id': "devices",
                'name': "Medical devices, including supply issues"
            },
            {
                'id': "medication",
                'name': "Medication, including supply issues"
            },
            {
                'id': "clinical-equipment",
                'name': "Other clinical equipment, including supply issues"
            },
            {
                'id': "buildings",
                'name': "Healthcare buildings, fixtures and fittings "
            },
            {
                "id": "it-error",
                "name": "IT system failure"
            },
            {
                'id': "other",
                'name': "Other Risks"
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
