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
      'Assessment delayed/omitted',
        'Unheralded cardiac arrest',
        'Operative incident',
        'Invasive procedure (not surgery)',
        'Airway obstruction/ Aspiration pneumonia',
        'Clinical diagnostic error including delay of diagnosis',
        'Deterioration not recognised or not acted on',
        'Fall',
        'Healthcare associated infection',
        'Medication incident',
        'Obstetric-specific incident',
        'Pressure ulcer grade 4 or above',
        'Pulmonary embolus  - hospital acquired',
        'Resuscitation (excluding medication)',
        'Suicide/severe self harm',
        'Transfer or discharge incident',
        'Other or unable to theme'
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
