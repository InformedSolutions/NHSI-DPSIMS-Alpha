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
        'themes': [
            {
                'id': "accident",
                'name': "Patient Accident"
            },
            {
                'id': "abuse",
                'name': "Patient abuse"
            },
            {
                'id': "infection",
                'name': "Infection Control"
            },
            {
                'id': "device",
                'name': "Medical Device  or equipment"
            },
            {
                'id': "disruptive",
                'name': "Disruptive Aggressive Behaviour"
            },
            {
                'id': "self-harm",
                'name': "Self-harming behaviour"
            },
            {
                'id': "consent",
                'name': "Consent, communication, confidentiality"
            },
            {
                'id': "clinical-assessment",
                'name': "Clinical assessment"
            },
            {
                'id': "infrastructure",
                'name': "Infrastructure"
            },
            {
                'id': "documentation",
                'name': "Documentation"
            },
            {
                'id': "access",
                'name': "Access, admission, transfer, discharge"
            },
            {
                'id': "treatment",
                'name': "Treatment procedures"
            },
            {
                'id': "medication",
                'name': "Medication"
            },
            {
                'id': "implementation",
                'name': "Implementation of care and ongoing monitoring/review"
            },
            {
                "id": "it-error",
                "name": "IT system failure"
            },
            {
                'id': "other",
                'name': "Other"
            }
        ],
        'subServiceAreas' : {
            'Not Known': [],
            'Acute': ['GENERAL SURGERY',
                'UROLOGY',
                'TRANSPLANTATION SURGERY',
                'BREAST SURGERY',
                'COLORECTAL SURGERY',
                'HEPATOBILIARY & PANCREATIC SURGERY',
                'UPPER GASTROINTESTINAL SURGERY',
                'VASCULAR SURGERY',
                'SPINAL SURGERY SERVICE',
                'TRAUMA & ORTHOPAEDICS',
                'ENT',
                'OPHTHALMOLOGY',
                'ORAL SURGERY',
                'RESTORATIVE DENTISTRY',
                'PAEDIATRIC DENTISTRY',
                'ORTHODONTICS',
                'MAXILLO-FACIAL SURGERY',
                'NEUROSURGERY',
                'PLASTIC SURGERY',
                'CARDIOTHORACIC TRANSPLANTATION',
                'ACCIDENT & EMERGENCY',
                'PAIN MANAGEMENT',
                'PAEDIATRIC UROLOGY',
                'PAEDIATRIC TRANSPLANTATION SURGERY',
                'BURNS CARE',
                'CARDIOTHORACIC SURGERY',
                'PAEDIATRIC SURGERY',
                'CARDIAC SURGERY',
                'THORACIC SURGERY',
                'PAEDIATRIC NEUROSURGERY',
                'PAEDIATRIC PLASTIC SURGERY',
                'PAEDIATRIC BURNS CARE',
                'PAEDIATRIC CARDIAC SURGERY',
                'PAEDIATRIC THORACIC SURGERY',
                'PAEDIATRIC GASTROINTESTINAL SURGERY',
                'PAEDIATRIC TRAUMA AND ORTHOPAEDICS',
                'PAEDIATRIC EAR NOSE AND THROAT',
                'PAEDIATRIC OPHTHALMOLOGY',
                'PAEDIATRIC MAXILLO-FACIAL SURGERY',
                'PAEDIATRIC CLINICAL HAEMATOLOGY',
                'PAEDIATRIC AUDIOLOGICAL MEDICINE',
                'PAEDIATRIC CLINICAL IMMUNOLOGY AND ALLERGY SERVICE',
                'PAEDIATRIC INFECTIOUS DISEASES',
                'PAEDIATRIC DERMATOLOGY',
                'PAEDIATRIC EPILEPSY',
                'PAEDIATRIC PAIN MANAGEMENT',
                'PAEDIATRIC INTENSIVE CARE',
                'PAEDIATRIC GASTROENTEROLOGY',
                'PAEDIATRIC ENDOCRINOLOGY',
                'PAEDIATRIC RESPIRATORY MEDICINE',
                'PAEDIATRIC NEPHROLOGY',
                'PAEDIATRIC MEDICAL ONCOLOGY',
                'PAEDIATRIC METABOLIC DISEASE',
                'PAEDIATRIC RHEUMATOLOGY',
                'PAEDIATRIC DIABETIC MEDICINE',
                'PAEDIATRIC CYSTIC FIBROSIS',
                'PAEDIATRIC INTERVENTIONAL RADIOLOGY',
                'COMMUNITY PAEDIATRICS',
                'PAEDIATRIC NEURO-DISABILITY',
                'ANAESTHETICS',
                'CRITICAL CARE MEDICINE',
                'GENERAL MEDICINE',
                'GASTROENTEROLOGY',
                'ENDOCRINOLOGY',
                'CLINICAL HAEMATOLOGY',
                'CLINICAL PHYSIOLOGY',
                'CLINICAL PHARMACOLOGY',
                'HEPATOLOGY',
                'DIABETIC MEDICINE',
                'BLOOD AND MARROW TRANSPLANTATION',
                'HAEMOPHILIA SERVICE',
                'AUDIOLOGICAL MEDICINE',
                'CLINICAL GENETICS',
                'CLINICAL IMMUNOLOGY and ALLERGY SERVICE',
                'REHABILITATION SERVICE',
                'PALLIATIVE MEDICINE',
                'CLINICAL IMMUNOLOGY',
                'ALLERGY SERVICE',
                'INTERMEDIATE CARE',
                'RESPITE CARE',
                'CARDIOLOGY',
                'PAEDIATRIC CARDIOLOGY',
                'CLINICAL MICROBIOLOGY',
                'SPINAL INJURIES',
                'ANTICOAGULANT SERVICE',
                'SPORT AND EXERCISE MEDICINE',
                'CARDIAC REHABILITATION',
                'STROKE MEDICINE',
                'TRANSIENT ISCHAEMIC ATTACK',
                'ADULT CYSTIC FIBROSIS SERVICE',
                'COMPLEX SPECIALISED REHABILITATION SERVICE',
                'SPECIALIST REHABILITATION SERVICE',
                'LOCAL SPECIALIST REHABILITATION SERVICE',
                'INFECTIOUS DISEASES',
                'DERMATOLOGY',
                'CONGENITAL HEART DISEASE SERVICE',
                'RESPIRATORY MEDICINE',
                'RESPIRATORY PHYSIOLOGY',
                'PROGRAMMED PULMONARY REHABILITATION',
                'TROPICAL MEDICINE',
                'GENITOURINARY MEDICINE',
                'NEPHROLOGY',
                'MEDICAL ONCOLOGY',
                'NUCLEAR MEDICINE',
                'NEUROLOGY',
                'CLINICAL NEUROPHYSIOLOGY',
                'RHEUMATOLOGY',
                'PAEDIATRICS',
                'PAEDIATRIC NEUROLOGY',
                'NEONATOLOGY',
                'WELL BABIES',
                'GERIATRIC MEDICINE',
                'DENTAL MEDICINE SPECIALTIES',
                'MEDICAL OPHTHALMOLOGY',
                'OBSTETRICS',
                'GYNAECOLOGY',
                'GYNAECOLOGICAL ONCOLOGY',
                'MIDWIFERY SERVICE',
                'PHYSIOTHERAPY',
                'OCCUPATIONAL THERAPY',
                'SPEECH AND LANGUAGE THERAPY',
                'PODIATRY',
                'DIETETICS',
                'ORTHOPTICS',
                'CLINICAL PSYCHOLOGY',
                'PROSTHETICS',
                'ORTHOTICS',
                'DRAMA THERAPY',
                'ART THERAPY',
                'MUSIC THERAPY',
                'OPTOMETRY',
                'PODIATRIC SURGERY',
                'LEARNING DISABILITY',
                'ADULT MENTAL ILLNESS',
                'CHILD and ADOLESCENT PSYCHIATRY',
                'FORENSIC PSYCHIATRY',
                'PSYCHOTHERAPY',
                'OLD AGE PSYCHIATRY',
                'EATING DISORDERS',
                'ADDICTION SERVICES',
                'LIAISON PSYCHIATRY',
                'PSYCHIATRIC INTENSIVE CARE',
                'PERINATAL PSYCHIATRY',
                'MENTAL HEALTH RECOVERY AND REHABILITATION SERVICE',
                'MENTAL HEALTH DUAL DIAGNOSIS SERVICE',
                'DEMENTIA ASSESSMENT SERVICE',
                'CLINICAL ONCOLOGY (previously RADIOTHERAPY)',
                'INTERVENTIONAL RADIOLOGY',
                'DIAGNOSTIC IMAGING',
                'CHEMICAL PATHOLOGY',
                'MEDICAL VIROLOGY',
                'AUDIOLOGY',
                'DIABETIC EDUCATION SERVICE',
                'Other'
            ],
            'Ambulance Trust': ['Ambulance Trust', 'Other'],
            'Community Nursing': ['Community Nursing', 'Other'],
            'Care Home': ['Care Home', 'Other'],
            'Dental Practice': ['Dental Practice', 'Other'],
            'Community Hospital': ['Community Hospital', 'Other'],
            'Community Pharmacy': ['Community Pharmacy', 'Other'],
            'General Practice': ['General Practice', 'Other'],
            'Social Care': ['Social Care', 'Other'],
            'Screening Service': [
                'NHS abdominal aortic aneurysm (AAA) programme',
                'NHS bowel cancer screening (BCSP) programme',
                'NHS breast screening (BSP) programme',
                'NHS cervical screening (CSP) programme',
                'NHS diabetic eye screening (DES) programme',
                'NHS fetal anomaly screening programme (FASP)',
                'NHS infectious diseases in pregnancy screening (IDPS) programme',
                'NHS newborn and infant physical examination (NIPE) screening programme',
                'NHS newborn blood spot (NBS) screening programme',
                'NHS newborn hearing screening programme (NHSP)',
                'NHS sickle cell and thalassaemia (SCT) screening programme'
                , 'Other'
            ]
        },
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
