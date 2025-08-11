export default {
    'server': {
        input: './api/server.json',
        output: {
            mode: 'single',
            target: './src/generated/api.ts',
            client: 'react-query',
            baseUrl: 'https://server.homecooking.vrba.dev'
        },
    },
};