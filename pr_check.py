import argparse
from github import Github, Auth

def main():
    parser = argparse.ArgumentParser(description="GitHub PR Checker")
    parser.add_argument('--token', required=True, help='GitHub Token')
    parser.add_argument('--owner', required=True, help='Repo Owner')
    parser.add_argument('--repo', required=True, help='Repo Name')
    parser.add_argument('--pr', required=True, type=int, help='PR Number')
    parser.add_argument('--list-members', action='store_true', help='List team members')

    args = parser.parse_args()

    auth = Auth.Token(args.token)
    g = Github(auth=auth)
    repo = g.get_user(args.owner).get_repo(args.repo)
    pr = repo.get_pull(args.pr)

    if args.list_members:
        collaborators = repo.get_collaborators()
        print("Team members:")
        for collab in collaborators:
            print(f"- {collab.login} (group: default)")
        return

    branch_name = pr.head.ref
    changes = pr.additions + pr.deletions
    print(f"PR #{pr.number}: Branch '{branch_name}', Changes: {changes} lines")

    if branch_name.startswith('feature/'):
        limit = 300
    elif branch_name.startswith('refactor/'):
        limit = 400
    elif branch_name.startswith('bugs/'):
        limit = 150
    else:
        print("Unknown branch type, skipping check")
        return

    if changes > limit:
        raise Exception(f"PR exceeds limit: {changes} > {limit} for {branch_name.split('/')[0]}")
    else:
        print("PR size check passed")

if __name__ == "__main__":
    main()
