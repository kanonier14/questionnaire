var QuestionTypeSwitcher = React.createClass({
    render: function() {
        return(
            <div>
                <ul>
                    <li className="question_type_item" onClick={this.props.addQuestion} data-type="RADIO">Radio button</li>
                    <li className="question_type_item" onClick={this.props.addQuestion} data-type="CHECKBOX">Checkbox</li>
                </ul>
            </div>
        );
    }
});