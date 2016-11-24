var QuestionTypeSwitcher = React.createClass({
    render: function() {
        return(
            <div>
                <ul>
                    <li onClick={this.props.addQuestion} data-type="RADIO">Radio button</li>
                    <li onClick={this.props.addQuestion} data-type="CHECKBOX">Checkbox</li>
                </ul>
            </div>
        );
    }
});