// This file is created to be available to run unit tests with localStorage usage
require('jsdom-global')(undefined, { url: 'https://localhost' });

global.localStorage = window.localStorage;
global.sessionStorage = window.sessionStorage;
window.Date = Date;