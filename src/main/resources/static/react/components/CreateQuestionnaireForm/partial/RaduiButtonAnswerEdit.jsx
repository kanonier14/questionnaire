var AnswerEditRadioButton = React.createClass({
    render: function() {
        let name = this.props.name;
        return(
            <div className="question-edit-form__answer-edit-text">
                <input data-required type="text" placeholder="введите ответ" name={name}></input>
                <a data-answername={name} onClick={this.props.removeAnswer}>X</a>
            </div>
        );
    }
});