// Use this file to change prototype configuration.

// Note: prototype config can be overridden using environment variables (eg on heroku)

module.exports = {
  // Service name used in header. Eg: 'Renew your passport'
  serviceName: 'Clinical Review',

  // Default port that prototype runs on
  port: '3001',

  // Enable or disable password protection on production
  useAuth: 'true',

  // Automatically stores form data, and send to all views
  useAutoStoreData: 'true',

  // Enable or disable built-in docs and examples.
  useDocumentation: 'true',

  // Force HTTP to redirect to HTTPs on production
  useHttps: 'false',

  // Cookie warning - update link to service's cookie page.
  cookieText: 'GOV.UK uses cookies to make the site simpler. <a href="#">Find out more about cookies</a>',

  // Enable or disable Browser Sync
  useBrowserSync: 'true',

    themes: [
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

    subthemes: [
        'Pre-Admission',
        'At admission (including ED)',
        'Operative',
        'Anaesthetic',
        'Both operative and anaesthetic',
        'Cancer - diagnostic error',
        'Respiratory or cardiovascular - diagnostic error',
        'Sepsis or infection - diagnostic error',
        'Skeletal or trauma - diagnostic error',
        'Other - diagnostic error',
        'Gastro Intestinal diagnostic error Previously "other"',
        'Adult - deterioration',
        'Child (under 18) - deterioration',
        'Fracture or brain injury - from fall',
        'No severe injury described but possible - from fall',
        'Baby - death or severe harm',
        'Mother - death or severe harm',
        'Inpatient on premises',
        'Inpatient off premises',
        'Clearly current community patient',
        'Possibly current community patient',
        'Other PSI related suicide/severe self harm',
        'New: In prison cell / custody suite'

    ]
}
