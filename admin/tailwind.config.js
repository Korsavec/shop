/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}",],
  theme: {
    extend: {
      fontFamily: {
        'Mochiy-Pop-One': ['Mochiy_Pop_One']
      }
    },
    screens: {
      '2xl': '1536px',
    },
    container: {
      center: true,
    },
  },
  plugins: [],
}
