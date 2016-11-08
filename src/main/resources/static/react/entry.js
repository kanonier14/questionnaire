var registeredComponents = new Map();

var components = {
    register: function(name, component) {
        registeredComponents.set(name, component);
    }
}

var application = {
    render: function() {
        var foundComponentsInstance = document.querySelectorAll('[data-component]');

        foundComponentsInstance.forEach( function(elem) {
         let componentName = elem.dataset.component;
         ReactDOM.render(React.createElement(registeredComponents.get(componentName)), elem);
        });
    }
}