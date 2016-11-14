var AnswerEditRadioButton = React.createClass({
    render: function() {
        let name = this.props.name;
        return(
            <div className="question-edit-form__answer-edit-text">
                <input type="text" placeholder="введите ответ" name={name}></input>
            </div>
        );
    }
});