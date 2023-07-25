const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'https://group04-cicid.onrender.com/',
      changeOrigin: true,
    })
  );
};