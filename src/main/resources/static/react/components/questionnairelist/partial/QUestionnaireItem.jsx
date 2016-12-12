var QuestionnaireItem = React.createClass({
    render: function() {
        let questionnaireLink = '/questionnaire/answer?id=' + this.props.idQuestionnaire;
        let questionnaireTitle = this.props.title;
        return(
            <a className="questionnaire_list_item_link" href={questionnaireLink}>
                <div className="questionnaire_list_item">
                    <div className="questionnaire_list_item__title">{questionnaireTitle}</div>
                </div>
            </a>
        );
    }
});