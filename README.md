# Salesforce Functions Demo

## Basic Steps
1. Get a Functions-enabled org (trial at [https://functions.salesforce.com/signups](https://functions.salesforce.com/signups))
1. Enable Dev Hub (Setup > Development > Dev Hub)
1. Enable Salesforce Functions (Setup > Feature Settings > Functions)
1. Clone or create an SFDX project
1. If creating a new project:
	1.	Generate an SFDX project `sf generate project -n MyProject -x`
	1. Ensure that functions are enabled in the scratch org config file (config/project-scratch-def.json) `features": ["Functions"]`
1. Create a scratch org
`sfdx force:org:create -s -f config/project-scratch-def.json -a FUNCTIONS002_SCRATCH`
1. Log into functions
`sf login functions`
1. Create a compute environment
`sf env create compute -o FUNCTIONS002_SCRATCH -a FUNCTIONS002_SCRATCH_COMPUTE`
1. Generate a function framework `sf generate function -n processimage -l java`
1. Check changes into the git repository
1. Deploy functions
`sf deploy functions -o FUNCTIONS002_SCRATCH`

## Useful Commands
* Connect to an org
	* SFDX: `sfdx force:auth:web:login -a ORG_ALIAS`
	* SF: `sf login org -a ORG_ALIAS`
* Set the default Dev Hub
	* SFDX: `sfdx config:set defaultdevhubusername=ORG_ALIAS`
	* SF: `sf config set target-dev-hub= ORG_ALIAS `
* List connections
	* SFDX: `sfdx force:org:list`
	* SF: `sf env list`
* Deploy Metadata
	* SFDX: `sfdx force:source:deploy -u ORG_ALIAS -m MetadataType:MetadataName`
	* SF: `sf deploy metadata -o ORG_ALIAS -m MetadataType:MetadataName`


## Useful Links

- [Getting Started with Salesforce Functions](https://developer.salesforce.com/docs/platform/functions/guide/index)
- [Salesforce Extensions Documentation](https://developer.salesforce.com/tools/vscode/)
- [Salesforce CLI Setup Guide](https://developer.salesforce.com/docs/atlas.en-us.sfdx_setup.meta/sfdx_setup/sfdx_setup_intro.htm)
- [Salesforce DX Developer Guide](https://developer.salesforce.com/docs/atlas.en-us.sfdx_dev.meta/sfdx_dev/sfdx_dev_intro.htm)
- [Salesforce CLI Command Reference](https://developer.salesforce.com/docs/atlas.en-us.sfdx_cli_reference.meta/sfdx_cli_reference/cli_reference.htm)
